package com.maven.service;

import com.maven.bank.Account;
import com.maven.bank.Customer;
import com.maven.dataStore.AccountType;
import com.maven.dataStore.CustomerRepo;

import java.math.BigDecimal;

public class AccountServiceImpl implements  AccountService{

    @Override
    public long openAccount(Customer theCustomer, AccountType type) throws MavenBankAccountException {
        if (theCustomer == null || type == null){
            throw new MavenBankAccountException("Customer and type required to open new account");
        }
        if (accountTypeExist(theCustomer,type)){
            throw new MavenBankAccountException("Customer already exist");
        }
        Account newAccount = new Account();
        newAccount.setAccountNumber(BankService.generateAccountNumber());
        newAccount.setType(type);
        CustomerRepo.getCustomer().put(theCustomer.getBvn(),theCustomer);
        return newAccount.getAccountNumber();
    }

    @Override
    public BigDecimal deposit(BigDecimal amount, long account) throws MavenBankAccountException {
        BigDecimal newBalance = BigDecimal.ZERO;
        Account depositAccount = findAccount(account);
       newBalance= depositAccount.getBalance().add(amount);
        return newBalance;
    }

    @Override
    public Account findAccount(long accountNumber) throws MavenBankAccountException {
        Account foundAccount = null;
        boolean accountFound = false;
        for (Customer customer: CustomerRepo.getCustomer().values()) {
            for (Account anAccount : customer.getAccounts()){
                if (anAccount.getAccountNumber() == accountNumber){
                    foundAccount = anAccount;
                    accountFound =true;
                    break;
                }
            }
            if (accountFound){
                break;
            }
        }
        return foundAccount;
    }

    @Override
    public Account findAccount(Customer customer, long account) throws MavenBankAccountException {
        return null;
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
