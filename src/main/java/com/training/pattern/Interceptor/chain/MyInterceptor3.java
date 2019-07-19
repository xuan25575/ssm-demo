package com.training.pattern.Interceptor.chain;

import com.training.pattern.Interceptor.Interceptor;

import java.lang.reflect.Method;

public class MyInterceptor3 implements Interceptor {

    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器3 】 的before 方法");
        return true;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {

    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.println("【拦截器3 】 after 方法");
    }
}
