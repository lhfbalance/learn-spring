package com.balance.spring.core;

import com.balance.spring.core.impl.EwechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @Autowired
    @Qualifier("SimpleServiceImpl")
    SimpleService simpleService;

    void test() {
        simpleService.hello();
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cat = new ClassPathXmlApplicationContext("classpath*:applicationContent.xml");
//        SimpleService simpleService = cat.getBean("SimpleServiceImpl", SimpleService.class);
//        simpleService.hello();
        Test test = cat.getBean(Test.class);
        test.test();
        Conf Conf = cat.getBean(Conf.class);
        System.out.println(Conf.getKey1());
        Server server = cat.getBean(Server.class);
        try {
            System.out.println(cat.getBean("EWechatService", EwechatService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
