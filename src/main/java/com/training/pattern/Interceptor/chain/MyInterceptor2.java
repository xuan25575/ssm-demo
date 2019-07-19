package com.training.pattern.Interceptor.chain;

import com.training.pattern.Interceptor.Interceptor;

import java.lang.reflect.Method;

public class MyInterceptor1 implements Interceptor {

    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.err.println("反射方法前逻辑");
        return false;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.err.println(" 取代了代理对象的方法");

    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.err.println(" 反射方法后逻辑");
    }
}
