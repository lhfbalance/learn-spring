package com.balance.spring.retry.stateful;

import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.classify.Classifier;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {

        int flag = 1;

        Map<Class<? extends Throwable>, Boolean> map = new HashMap();
        map.put(RollbackException.class, true);

        Classifier<? super Throwable, Boolean> classifier = new BinaryExceptionClassifier(map);

        RetryTemplate retryTemplate = new RetryTemplate();
        try {
            retryTemplate.execute(new RetryCallback<Void, Throwable>() {
                @Override
                public Void doWithRetry(RetryContext context) throws Throwable {

                    switch (flag) {
                        case 0:
                            throw new RollbackException("rollback");
                        case 1:
                            throw new UnRollbackException("unRollback");
                    }

                    return null;
                }
            }, new DefaultRetryState("my-retry", true, classifier));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
