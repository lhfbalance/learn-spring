package com.balance.spring.retry.breaker;

import com.balance.spring.retry.BizService;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class Demo {
    
    public static void main(String[] args) {
        RetryTemplate retryTemplate = new RetryTemplate();

        CircuitBreakerRetryPolicy policy = new CircuitBreakerRetryPolicy();

        BizService bizService = new BizService();

        try {
            retryTemplate.execute(new RetryCallback<Void, Throwable>() {
                @Override
                public Void doWithRetry(RetryContext context) throws Throwable {
                    if (context.getRetryCount() != 0)
                        System.out.println(System.currentTimeMillis() - (long)context.getAttribute("lasttime"));
                    context.setAttribute("lasttime", System.currentTimeMillis());
                    System.out.println("retry: " + context.getRetryCount());
                    bizService.neverSuccess();
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
