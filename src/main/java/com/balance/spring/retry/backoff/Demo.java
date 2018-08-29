package com.balance.spring.retry.backoff;

import com.balance.spring.retry.BizService;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    
    public static void main(String[] args) {
        RetryTemplate retryTemplate = new RetryTemplate();


        SimpleRetryPolicy policy = new SimpleRetryPolicy(5);
        retryTemplate.setRetryPolicy(policy);

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(100);
        backOffPolicy.setMultiplier(3);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        BizService biz = new BizService();

        try {

            retryTemplate.execute(new RetryCallback<Void, Throwable>() {
                @Override
                public Void doWithRetry(RetryContext context) throws Throwable {
                    if (context.getRetryCount() != 0)
                        System.out.println(System.currentTimeMillis() - (long)context.getAttribute("lasttime"));
                    context.setAttribute("lasttime", System.currentTimeMillis());
                    System.out.println("retry for: " + context.getRetryCount());
                    if (context.getLastThrowable() != null)
                        context.getLastThrowable().printStackTrace();
                    biz.biz("");
                    return null;
                }
            });

        } catch (Throwable throwable) {
            System.out.println("not intercept, " + throwable.getMessage());
//            throwable.printStackTrace();
        }
    }

}
