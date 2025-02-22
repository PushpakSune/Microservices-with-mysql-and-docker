package com.pbank.service.impl;

import com.pbank.constants.AccountsConstants;
import com.pbank.dto.AccountsDto;
import com.pbank.dto.CustomerDto;
import com.pbank.entity.Accounts;
import com.pbank.entity.Customer;
import com.pbank.exception.CustomerAlreadyExistsException;
import com.pbank.exception.ResourceNotFoundException;
import com.pbank.mapper.AccountsMapper;
import com.pbank.mapper.CustomerMapper;
import com.pbank.repository.AccountsRepository;
import com.pbank.repository.CustomerRepository;
import com.pbank.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
 @AllArgsConstructor // no need to Autowired Dependancy because only one Constructor
public class AccountsServiceImpl implements IAccountsService {
    AccountsRepository accountsRepository;
    CustomerRepository customerRepository;

/*
    public AccountsServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }
*/

    @Override
    public void createAccount(CustomerDto customerDto) {
        log.error("****************    pbank createAccount method in Service layer Starts -----------------------------");
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> existedCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (existedCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exist with given mobile number "+customerDto.getMobileNumber());
        }

      /*  customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");*/ //we are commenting this because we have implemented auditor logic
        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createNewAccount(savedCustomer));
        log.error("****************    pbank createAccount method in Service layer Ends -----------------------------");

    }
    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        log.error("****************    pbank createNewAccount method in Service layer Starts -----------------------------");

        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        /*customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");*/ //we are commenting this because we have implemented auditor logic
        log.error("****************    pbank createNewAccount method in Service layer Ends -----------------------------");

        return newAccount;
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        log.error("****************    pbank fetchAccount method in Service layer Starts -----------------------------");

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account ", "customerId ", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        log.error("****************    pbank fetchAccount method in Service layer Ends -----------------------------");

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        log.error("****************    pbank updateAccount method in Service layer Starts -----------------------------");

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        log.error("****************    pbank updateAccount method in Service layer Ends -----------------------------");

        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
