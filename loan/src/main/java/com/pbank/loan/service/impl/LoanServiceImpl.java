package com.pbank.loan.service.impl;


import com.pbank.loan.constants.LoansConstants;
import com.pbank.loan.dto.LoansDto;
import com.pbank.loan.entity.Loans;
import com.pbank.loan.exception.LoanAlreadyExistsException;
import com.pbank.loan.exception.ResourceNotFoundException;
import com.pbank.loan.mapper.LoansMapper;
import com.pbank.loan.respsitory.LoansRepository;
import com.pbank.loan.service.LoanService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service


public class LoanServiceImpl implements LoanService {

    LoansRepository loansRepository;

    Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

    public LoanServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @Override
    public void createLoan(String mobileNumber) {
      /*  Loans loans = loansRepository
                .findByLoanNumber(mobileNumber)
                .orElseThrow(() -> throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber"+mobileNumber));
*/
        Optional<Loans> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));


    }
    private Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);

        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;

    }


    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.
                findByMobileNumber(mobileNumber).
                orElseThrow(() ->  new ResourceNotFoundException("Loan", "mobile Number", mobileNumber));
        return LoansMapper.mapToDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        System.out.println("Entered loans DTO : "+loansDto);

        Loans loans = loansRepository
                .findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile Number", loansDto.getMobileNumber()));
        System.out.println("Entered loans Entity before mapping : "+loans);
        LoansMapper.mapToEntity(loansDto, loans);
        System.out.println("Entered loans Entity after mapping : "+loans);
        Loans save = loansRepository.save(loans);
        System.out.println("Saved loans Entity after mapping : "+save);

        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan",
                        "mobile Number", mobileNumber));

        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
