package com.maven.service;

import com.maven.bank.Customer;
import com.maven.dataStore.AccountType;
import com.maven.dataStore.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
    Customer john;
    Customer jane;
    AccountService accountService;

    @BeforeEach
    void setup(){
        john = new Customer();
        jane = new Customer();
        accountService = new AccountServiceImpl();
        john.setBvn(BankService.generateBVN());
        john.setSurname("");
        john.setFirstName("");
        john.setEmail("");

    }

    @Test
    void openAccount()  {
        try {
            assertTrue(CustomerRepo.getCustomer().isEmpty());
            assertEquals(1,CustomerRepo.getCustomer().containsKey(john.getBvn()));
            assertFalse(CustomerRepo.getCustomer().containsKey(john.getBvn()));
            long newAccountNumber = accountService.openAccount(john, AccountType.SAVING);

            assertFalse(CustomerRepo.getCustomer().isEmpty());
            assertEquals(1,BankService.getCurrentAccountNumber());
            assertTrue(CustomerRepo.getCustomer().containsKey(john.getBvn()));
            assertFalse(john.getAccounts().isEmpty());
            assertEquals(newAccountNumber,john.getAccounts().get(0).getAccountNumber());
        } catch (MavenBankAccount mavenBankAccount) {
            mavenBankAccount.printStackTrace();
        }

    }
}