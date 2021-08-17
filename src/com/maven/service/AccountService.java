package com.maven.service;

import com.maven.bank.Account;
import com.maven.bank.Customer;
import com.maven.dataStore.AccountType;

import java.math.BigDecimal;

public interface AccountService {
    long openAccount(Customer theCustomer, AccountType type) throws MavenBankAccountException;

    BigDecimal deposit(BigDecimal Balance, long account);
    Account findAccount(long accountNumber) throws  MavenBankAccountException;
    Account findAccount(Customer customer, long account) throws MavenBankAccountException;
}
