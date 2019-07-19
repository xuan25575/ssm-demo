package com.training.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler {

    private  Object target;

    public JdkProxyExample (Object target){
        this.target= target;
    }

    /**
     * Object target;真实对象
     * @return  代理对象
     */
    public Object createProxy(){
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用之前");
        Object result = method.invoke(target, args);
        System.out.println("之后");
        return result;
    }
}
