package com.balance.spring.retry.stateful;

public class RollbackException extends Exception {

    public RollbackException() {
        super();
    }

    public RollbackException(String message) {
        super(message);
    }

    public RollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollbackException(Throwable cause) {
        super(cause);
    }

    protected RollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
