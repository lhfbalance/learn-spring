package com.balance.spring.retry;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BizService {


    public void biz(String arg) throws IOException, ClassNotFoundException, InterruptedException {

        Random random = new Random(9);
        int i = random.nextInt(20) % 3;
        System.out.println("biz: " + i);

        switch (i) {
            case 0:
                System.out.println("need retry");
                throw new IOException("retry");
            case 1:
                System.out.println("do not retry");
                throw new ClassNotFoundException("not retry");
            case 2:
                System.out.println("do not catch");
                throw new InterruptedException("test");
                default:
                    break;
        }

    }

    public void neverSuccess() throws Exception {
        TimeUnit.MILLISECONDS.sleep(100);
        throw new Exception("neverSuccess");
    }

    public void useLongTime() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        throw new TimeoutException("for 10 seconds");
    }

}
