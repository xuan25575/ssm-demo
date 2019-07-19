package com.training.pattern.proxy;


import org.junit.Test;
public class TestDemo {


    @Test
    public void Test01 () {
        HelloWorld proxy = (HelloWorld)new JdkProxyExample(new HelloWorldimpl()).createProxy();
        String s = proxy.sayHelloWorld("zhang san ");
        System.out.println(s);
    }

    @Test
    public void Test02 () {
        HelloWorld proxy = (HelloWorld)new CglibProxyExample().getProxy(HelloWorldimpl.class);
        String s = proxy.sayHelloWorld("zhang san ");
        System.out.println(s);
    }
}
