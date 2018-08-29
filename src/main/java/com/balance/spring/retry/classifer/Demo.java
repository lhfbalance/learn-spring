package com.balance.spring.retry.classifer;

import com.balance.spring.retry.BizService;
import com.balance.spring.retry.MyException;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        Map<Class<? extends Throwable>, Boolean> map = new HashMap();
        map.put(IOException.class, true);
        map.put(ClassNotFoundException.class, false);


        RetryTemplate retryTemplate = new RetryTemplate();

        int debug = 2;

        SimpleRetryPolicy policy = new SimpleRetryPolicy(3, map, true, false);
        retryTemplate.setRetryPolicy(policy);


        try {

            retryTemplate.execute(new RetryCallback<Void, Throwable>() {
                @Override
                public Void doWithRetry(RetryContext context) throws Throwable {
                    if (context.getRetryCount() != 0)
                        System.out.println(System.currentTimeMillis() - (long)context.getAttribute("lasttime"));
                    context.setAttribute("lasttime", System.currentTimeMillis());
                    System.out.println("retry for: " + context.getRetryCount());
                    switch (debug) {
                        case 0:
                            throw new IOException("retry");
                        case 1:
                            throw new ClassNotFoundException("do not retry");
                        case 2:
                            throw new FileNotFoundException("sub of IoException");
                        case 3:
                            throw new NullPointerException("default, do not retry");
                        case 4:
                            throw new MyException("travese", new IOException());
                            default:
                                ;
                    }
                    return null;
                }
            });

        } catch (Throwable throwable) {
            System.out.println("not intercept, " + throwable.getMessage());
//            throwable.printStackTrace();
        }

    }

}
