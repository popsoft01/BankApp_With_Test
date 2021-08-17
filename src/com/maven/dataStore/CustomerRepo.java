package com.maven.dataStore;

import com.maven.bank.Account;
import com.maven.bank.Customer;
import com.maven.service.AccountServiceImpl;
import com.maven.service.BankService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepo {
    private static Map<Long, Customer> customer = new HashMap<>();

    public static Map<Long, Customer> getCustomer() {
        return customer;
    }

    private static void setCustomer(Map<Long, Customer> customer) {
        CustomerRepo.customer = customer;
    }


    static {
        Customer john = new Customer();
        john = new Customer ();
        john.setBvn (BankService.generateBVN ());
        john.setEmail ("john@doe.com");
        john.setFirstName ("john");
        john.setSurname ("doe");
        john.setPhone ("12345678901");
        Account johnSavingAccount = new Account(1,AccountType.SAVING);
        john.getAccounts().add(johnSavingAccount);

        Account johnCurrentAccount = new Account(2,new BigDecimal(50000000),AccountType.CURRENT);
        john.getAccounts().add(johnCurrentAccount);

       Customer jane = new Customer ();
        jane.setBvn (BankService.generateBVN ());
        jane.setEmail ("john@doe.com");
        jane.setFirstName ("john");
        jane.setSurname ("doe");
        jane.setPhone ("12345678901");
        Account janeSavingAccount = new Account(1,AccountType.SAVING);
        john.getAccounts().add(janeSavingAccount);

    }

}
