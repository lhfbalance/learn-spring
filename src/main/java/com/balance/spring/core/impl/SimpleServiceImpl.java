package com.balance.spring.core.impl;

import com.balance.spring.core.SimpleService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("SimpleServiceImpl")
public class SimpleServiceImpl implements SimpleService {

    @Override
    public void hello() {
        System.out.println("hello");
    }
}
