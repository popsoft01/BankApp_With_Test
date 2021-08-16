package com.maven.service;

import com.maven.bank.Account;
import com.maven.bank.Customer;
import com.maven.dataStore.AccountType;
import com.maven.dataStore.CustomerRepo;
import com.maven.dataStore.MavenBankAccountDataStore;

public class AccountServiceImpl implements  AccountService{

    @Override
    public long openAccount(Customer theCustomer, AccountType type) throws MavenBankAccount {
        if (theCustomer == null || type == null){
            throw new MavenBankAccount("Customer and type required to open new account");
        }
        if (accountTypeExist(theCustomer,type)){
            throw new MavenBankAccount("Customer already exist");
        }
        Account newAccount = new Account();
        newAccount.setAccountNumber(BankService.generateAccountNumber());
        newAccount.setType(type);
        CustomerRepo.getCustomer().put(theCustomer.getBvn(),theCustomer);
        return newAccount.getAccountNumber();
    }
    public  boolean accountTypeExist(Customer aCustomer, AccountType type){
        boolean accounntTYpeExit = false;
        for (Account customerAccount: aCustomer.getAccounts()){
            if (customerAccount.getType() == type){
                accounntTYpeExit = true;
                break;
            }
        }

        return accounntTYpeExit;
    }
}
