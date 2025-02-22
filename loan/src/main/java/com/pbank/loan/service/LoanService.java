package com.pbank.loan.service;


import com.pbank.loan.dto.LoansDto;

public interface LoanService {

    void createLoan(String mobileNumber);


    LoansDto fetchLoan(String mobileNumber);


    boolean updateLoan(LoansDto loansDto);


    boolean deleteLoan(String mobileNumber);
}
