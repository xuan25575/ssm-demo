package com.training.pattern.proxy;

public class HelloWorldimpl implements HelloWorld {

    @Override
    public String sayHelloWorld(String name) {
        System.out.println("................");
        return "Hello world ," +name;
    }
}
