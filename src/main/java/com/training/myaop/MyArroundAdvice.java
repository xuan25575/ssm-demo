package com.training.myaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;


public class MyArroundAdvice implements MethodInterceptor {

    // 环绕通知
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        //Object proceed = methodInvocation.proceed();
        MyTarget target = new MyTarget();
        Object invoke = methodInvocation.getMethod().invoke(target);
        after();

        return invoke;
    }


    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }


    // 测试spring  aop 功能。 --》 通知增强
    // spring 中切面应该是顾问  --》 顾问 = 通知 + 切入点
    public static void main(String[] args) {
        ProxyFactory proxyFactory =  new ProxyFactory(); // 创建代理工厂
        proxyFactory.setTarget(new MyTarget()); // 设置目标对象
        proxyFactory.addAdvice(new MyArroundAdvice()); // 设置增强方法--》通知。
        MyTarget proxy = (MyTarget)proxyFactory.getProxy(); // 获取代理
        proxy.say();
    }
}
