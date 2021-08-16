package com.maven.bank;

import com.maven.dataStore.AccountType;
import com.maven.dataStore.CustomerRepo;
import com.maven.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {
    Customer john;
    Account johnAccount;
    @BeforeEach
    void setUp(){
        john = new Customer();
        johnAccount = new Account();
    }

    @Test
    void  openAccount(){
        john.setBvn(BankService.generateBVN());
        john.setEmail("poptun@mail.com");
        john.setFirstName("pop");
        john.setSurname("tun");
        john.setPhone("0988765544");

        johnAccount.setAccountNumber(BankService.generateAccountNumber());
        johnAccount.setBalance(new BigDecimal(5000));
        johnAccount.setAccountId("");
        johnAccount.setType(AccountType.SAVING);

        assertTrue(CustomerRepo.getCustomer().isEmpty());
    }
}
