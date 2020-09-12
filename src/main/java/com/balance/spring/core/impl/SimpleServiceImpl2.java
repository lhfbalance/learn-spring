package com.balance.spring.core.impl;

import com.balance.spring.core.SimpleService;
import org.springframework.stereotype.Service;

@Service("SimpleServiceImpl2")
public class SimpleServiceImpl2 implements SimpleService {

    @Override
    public void hello() {
        System.out.println("hello 222");
    }
}
