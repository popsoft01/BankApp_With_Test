package com.maven.service;

import com.maven.bank.Account;
import com.maven.bank.Customer;
import com.maven.dataStore.AccountType;
import com.maven.dataStore.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
    private AccountService accountService;
    private Customer abu;
    private Customer jane;
    private Account johnAccount;
    private Account janeAccount2;

    @BeforeEach
    void setUp(){
        accountService = new AccountServiceImpl ();
        abu = new Customer ();
        abu.setBvn (BankService.generateBVN ());
        abu.setEmail ("john@doe.com");
        abu.setFirstName ("john");
        abu.setSurname ("doe");
        abu.setPhone ("12345678901");
        johnAccount = new Account();
        janeAccount2 = new Account();

        jane = new Customer ();
        jane.setBvn (BankService.generateBVN());
        jane.setEmail ("jane@blackie.com");
        jane.setFirstName ("jane");
        jane.setSurname ("blackie");
        jane.setPhone ("90876543211");
    }
    @Test
    void openSavingsAccount(){

        assertTrue(CustomerRepo.getCustomer().isEmpty());
        assertEquals (0, BankService.getCurrentAccountNumber());
        assertFalse (CustomerRepo.getCustomer ().containsKey (abu.getBvn ()));
        try {
            long newAccountNumber = accountService.openAccount (abu, AccountType.SAVING);
            assertFalse (CustomerRepo.getCustomer ( ).isEmpty ( ));
            assertEquals (1, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomer( ).containsKey (abu.getBvn ( )));
            assertFalse (abu.getAccounts( ).isEmpty ( ));
            System.out.println (abu.getAccounts( ).get (0));
            assertEquals (newAccountNumber, abu.getAccounts ( ).get (0).getAccountNumber ( ));
        }catch(MavenBankAccountException ex){
            ex.printStackTrace ();
        }

    }

    @Test
    void openAccountWithNoCustomer(){
        assertThrows (MavenBankAccountException.class, ()-> accountService.openAccount (null, AccountType.SAVING));
    }

    @Test
    void openAccountWithNoAccountType(){
        assertThrows (MavenBankAccountException.class, ()-> accountService.openAccount (abu,null));
    }

    @Test
    void openTheSameTypeOfAccountForTheSameCustomer(){
        try{
            long newAccountNumber = accountService.openAccount (abu, AccountType.SAVING);
            assertFalse (CustomerRepo.getCustomer ( ).isEmpty ( ));
            assertEquals (1, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomer ( ).containsKey (abu.getBvn ( )));
            assertFalse (abu.getAccounts ( ).isEmpty ( ));
            System.out.println (abu.getAccounts ( ).get (0));
            assertEquals (newAccountNumber, abu.getAccounts ( ).get (0).getAccountNumber ( ));
        } catch (MavenBankAccountException e) {
            e.printStackTrace ( );
        }

        assertThrows (MavenBankAccountException.class, ()-> accountService.openAccount (abu, AccountType.SAVING));
        assertEquals (1,BankService.getCurrentAccountNumber ());
        assertEquals (1, abu.getAccounts ().size ());
    }

    @Test
    void openAccountForCurrentAccount(){
        assertTrue(CustomerRepo.getCustomer ().isEmpty ());
        assertEquals (0, BankService.getCurrentAccountNumber());
        assertFalse (CustomerRepo.getCustomer ().containsKey (abu.getBvn ()));
        try {
            long newAccountNumber = accountService.openAccount (abu, AccountType.CURRENT);
            assertFalse (CustomerRepo.getCustomer ( ).isEmpty ( ));
            assertEquals (1, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomer ( ).containsKey (abu.getBvn ( )));
            assertFalse (abu.getAccounts ( ).isEmpty ( ));
            System.out.println (abu.getAccounts ( ).get (0));
            assertEquals (newAccountNumber, abu.getAccounts ( ).get (0).getAccountNumber ( ));
        }catch(MavenBankAccountException ex){
            ex.printStackTrace ();
        }
    }


    @Test
    void openDifferentTypeOfAccountForTheSameCustomer(){
        try{
            long newAccountNumber = accountService.openAccount (abu, AccountType.SAVING);
            assertFalse (CustomerRepo.getCustomer ( ).isEmpty ( ));
            assertEquals (1, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomer ( ).containsKey (abu.getBvn ( )));
            assertEquals (1, abu.getAccounts ().size ());
            assertEquals (newAccountNumber, abu.getAccounts ( ).get (0).getAccountNumber ( ));
            newAccountNumber = accountService.openAccount (abu, AccountType.CURRENT);
            assertEquals (2, BankService.getCurrentAccountNumber ( ));
            assertEquals (2, abu.getAccounts ().size ());
            assertEquals (newAccountNumber, abu.getAccounts ().get (1).getAccountNumber ());

        } catch (MavenBankAccountException e) {
            e.printStackTrace ( );
        }

    }

    @Test
    void openSavingsAccountForANewCustomer(){

        assertTrue(CustomerRepo.getCustomer ().isEmpty ());
        assertEquals (0, BankService.getCurrentAccountNumber());
        assertFalse (CustomerRepo.getCustomer ().containsKey (abu.getBvn ()));
        try {
            long newAccountNumber = accountService.openAccount (abu, AccountType.SAVING);
            assertFalse (CustomerRepo.getCustomer( ).isEmpty ( ));
            assertEquals (1, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomer ( ).containsKey (abu.getBvn ( )));
            assertFalse (abu.getAccounts ( ).isEmpty ( ));
            assertEquals (newAccountNumber, abu.getAccounts ( ).get (0).getAccountNumber ( ));

            newAccountNumber = accountService.openAccount (jane, AccountType.SAVING);
            assertEquals (2, CustomerRepo.getCustomer ().size ());
            assertEquals (2, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomer ( ).containsKey (jane.getBvn ( )));
            assertFalse (jane.getAccounts ( ).isEmpty ( ));
            assertEquals (1, jane.getAccounts ().size ());
            assertEquals (newAccountNumber, jane.getAccounts ( ).get (0).getAccountNumber ( ));
            assertEquals (1, abu.getAccounts ().size ());
        }catch(MavenBankAccountException ex){
            ex.printStackTrace ();
        }

    }
    @Test
    void deposit() throws MavenBankAccountException {
        try {
            long newAccountNumber = accountService.openAccount(abu, AccountType.SAVING);
            assertFalse(CustomerRepo.getCustomer().isEmpty());
            assertNull(abu.getAccounts().get(0).getBalance());
            long accountNumber = abu.getAccounts().get(0).getAccountNumber();
            BigDecimal accountBalance = accountService.deposit(new BigDecimal(5000), accountNumber);
            ;
            assertNotNull(abu.getAccounts().get(0).getBalance());
        }catch (MavenBankTransactionException e){
            e.printStackTrace();

        }catch (MavenBankAccountException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAccount() throws MavenBankAccountException {
        try{
            long newAccountNumber = accountService.openAccount(abu, AccountType.SAVING);
            newAccountNumber = accountService.openAccount(abu, AccountType.CURRENT);
            newAccountNumber = accountService.openAccount(jane,AccountType.SAVING);
            assertFalse(CustomerRepo.getCustomer().isEmpty());
            assertEquals(2,CustomerRepo.getCustomer().size());
            assertEquals(3,BankService.getCurrentAccountNumber());

            Account johnCurrentAccount = accountService.findAccount(2);
            assertNotNull(johnCurrentAccount);
        }catch (MavenBankAccountException ex){
            ex.printStackTrace();
        }
    }
}