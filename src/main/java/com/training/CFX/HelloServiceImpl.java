package com.training.CFX;

import javax.jws.WebService;

@WebService
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String name) {
        return "hello"  + name;
    }
}
