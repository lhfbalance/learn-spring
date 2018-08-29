package com.balance.spring.retry.simple;

public interface Result {

    int getTimes();

    boolean isSuccess();

    String getMessage();

}
