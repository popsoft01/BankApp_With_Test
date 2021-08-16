package com.maven.service;

import com.maven.bank.Customer;
import com.maven.dataStore.AccountType;

public interface AccountService {
    long openAccount(Customer theCustomer, AccountType type) throws MavenBankAccount;
}
