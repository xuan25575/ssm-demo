package com.training.pattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

// 生成子类代理。
public class CglibProxyExample implements MethodInterceptor {
    /**
     *
     * @param cls
     * @return class 类的代理对象
     */
    public Object getProxy(Class cls){
        Enhancer enhancer = new Enhancer();
        // 增强类型
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this); // 回调当前接口。
        return enhancer.create();
       // Enhancer.create(cls,this);  一行代码
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before ");
        // invokeSuper  调用真实对象方法
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("after  ");
        return result;
    }
}
