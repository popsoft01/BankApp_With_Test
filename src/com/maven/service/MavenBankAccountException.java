package com.maven.service;

public class MavenBankAccountException extends Throwable {
    public MavenBankAccountException() {
    }

    public MavenBankAccountException(String message) {
        super(message);
    }

    public MavenBankAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public MavenBankAccountException(Throwable cause) {
        super(cause);
    }

    public MavenBankAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
