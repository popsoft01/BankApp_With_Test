package com.maven.service;

public class BankService {
    private static long currentBVN =2;
    private static long currentAccount =3;

    public static long generateBVN(){
        currentBVN++;
        return currentBVN;
    }
    public static long generateAccountNumber(){
        currentAccount++;
        return currentAccount;
    }

    public static long getCurrentBVN() {
        return currentBVN;
    }

    private static void setCurrentBVN(long currentBVN) {
        BankService.currentBVN = currentBVN;
    }

    public static long getCurrentAccountNumber() {
        return currentAccount;
    }

    private static void setCurrentAccount(long currentAccount) {
        BankService.currentAccount = currentAccount;
    }
}
