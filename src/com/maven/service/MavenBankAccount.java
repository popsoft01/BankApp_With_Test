package com.maven.service;

public class MavenBankAccount extends Throwable {
    public MavenBankAccount() {
    }

    public MavenBankAccount(String message) {
        super(message);
    }

    public MavenBankAccount(String message, Throwable cause) {
        super(message, cause);
    }

    public MavenBankAccount(Throwable cause) {
        super(cause);
    }

    public MavenBankAccount(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
