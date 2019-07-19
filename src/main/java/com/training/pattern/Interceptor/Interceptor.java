package com.training.pattern.Interceptor;

import java.lang.reflect.Method;

// 拦截器实现就是动态代理。  通过拦截器接口可以简化开发，和处理逻辑。
public interface Interceptor {
    boolean before(Object proxy, Object target, Method method, Object[]
            args);

    void around(Object proxy, Object target, Method method, Object[]
            args);

    void after(Object proxy, Object target, Method method, Object[]
            args);
}
