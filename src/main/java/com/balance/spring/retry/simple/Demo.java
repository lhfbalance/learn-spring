package com.balance.spring.retry.simple;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class Demo {

    public static final String MYRESULT = "MYRESULT";

    public static void main(String[] args) throws Throwable {
        RetryTemplate retryTemplate = new RetryTemplate();

//        TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(10);

        retryTemplate.setRetryPolicy(policy);



        Result result = retryTemplate.execute(new RetryCallback<Result, Throwable>() {
            @Override
            public Result doWithRetry(RetryContext context) throws Throwable {

                DefaultResult res = (DefaultResult) context.getAttribute(MYRESULT);
                if (res == null) {
                    System.out.println("res is null");
                    res = new DefaultResult();
                    context.setAttribute(MYRESULT, res);
                }

                if (context.getRetryCount() != 0) {
                    res.addRetryTimes();
                }

                System.out.println(res.getTimes());

                if (res.getTimes() != 5)
                    throw new Exception("times got 5");
                else {
                    res.setSuccess();
                    res.setMessage("well, we completed a retry job");
                }
                return res;
            }

        });

        System.out.println(result.getTimes());
        System.out.println(result.isSuccess());
        System.out.println(result.getMessage());
    }

}
