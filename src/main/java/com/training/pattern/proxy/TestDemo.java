package com.training.pattern.proxy;


import org.
public class Test {


    public  void main(String[] args) {
        HelloWorld proxy = (HelloWorld)new JdkProxyExample(new HelloWorldimpl()).createProxy();
        String s = proxy.sayHelloWorld("zhang san ");
        System.out.println(s);
    }
}
