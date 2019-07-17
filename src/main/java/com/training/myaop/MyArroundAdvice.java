package com.training.myaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;


public class MyArroundAdvice implements MethodInterceptor {

    // 环绕通知
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object proceed = methodInvocation.proceed();
        after();
        return proceed;
    }


    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }


    // 测试  spring  aop 功能。
    public static void main(String[] args) {
        ProxyFactory proxyFactory =  new ProxyFactory();
        proxyFactory.setTarget(new MyTarget());
        proxyFactory.addAdvice(new MyArroundAdvice());
        MyTarget proxy = (MyTarget)proxyFactory.getProxy();
        proxy.say();
    }
}
