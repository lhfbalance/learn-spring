package com.balance.spring.retry.stateful;

public class UnRollbackException extends Exception {


    public UnRollbackException() {
        super();
    }

    public UnRollbackException(String message) {
        super(message);
    }

    public UnRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnRollbackException(Throwable cause) {
        super(cause);
    }

    protected UnRollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
