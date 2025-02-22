package com.pbank.loan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;


public class LoansDto {
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    private String loanNumber;
    @NotEmpty(message = "LoanType can not be a null or empty")
    private String loanType;


    @Positive(message = "Total loan amount should be greater than zero")
    private int totalLoan;
    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    private int outstandingAmount;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(int totalLoan) {
        this.totalLoan = totalLoan;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(int outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public LoansDto() {
    }

    public LoansDto(String mobileNumber, String loanNumber, String loanType, int totalLoan, int amountPaid, int outstandingAmount) {
        this.mobileNumber = mobileNumber;
        this.loanNumber = loanNumber;
        this.loanType = loanType;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmount = outstandingAmount;
    }

    @Override
    public String toString() {
        return "LoansDto\n{" +
                " mobileNumber='" + mobileNumber + '\'' +
                ", loanNumber='" + loanNumber + '\'' +
                ", loanType='" + loanType + '\'' +
                ", totalLoan=" + totalLoan +
                ", amountPaid=" + amountPaid +
                ", outstandingAmount=" + outstandingAmount +
                '}';
    }
}
