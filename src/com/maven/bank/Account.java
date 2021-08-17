package com.maven.bank;

import com.maven.dataStore.AccountType;

import java.math.BigDecimal;

public class Account {
    private long accountNumber;
    private BigDecimal balance;
    private AccountType type;
    private String accountId;

    public Account() {
    }

    public Account(long accountNumber, AccountType type) {
        this.accountNumber = accountNumber;
        this.type = type;
    }

    public Account(long accountNumber, BigDecimal balance, AccountType type) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


}
