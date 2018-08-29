package com.balance.spring.retry.simple;

public class DefaultResult implements Result {

    int times;
    boolean success;
    String message;

    public DefaultResult() {
        times = 0;
        success = false;
    }

    public void addRetryTimes() {
        times++;
    }

    public void setSuccess() {
        success = true;
    }

    public void setFailure() {
        success = false;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getTimes() {
        return times;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
