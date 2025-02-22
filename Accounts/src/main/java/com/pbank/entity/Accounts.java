package com.pbank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity


@Setter @Getter @NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity {

    private Long customerId;

    @Id  //We are not writing any generation strategy because we want account number to be created manually
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

}
