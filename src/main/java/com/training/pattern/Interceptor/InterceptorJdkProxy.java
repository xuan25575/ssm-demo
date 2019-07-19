package com.training.pattern.Interceptor;

import com.training.pattern.proxy.HelloWorld;
import com.training.pattern.proxy.HelloWorldimpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterceptorJdkProxy implements InvocationHandler {
    private  Object target;

    private String interceptorClass = null; // 拦截器权限定类名

    public InterceptorJdkProxy(Object target,String interceptorClass){
        this.target= target;
        this.interceptorClass = interceptorClass;
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

        if(interceptorClass == null){
            return method.invoke(target,args);
        }
        Object result = null;
        Interceptor interceptor = (Interceptor) Class.forName(interceptorClass) .newInstance();
        if(interceptor.before(proxy,target,method,args)){
            // 反射原有方法
            result = method.invoke(target,args);
        }else {
            // 返回false 的方法
            interceptor.around(proxy,target,method,args);
        }
        //调用后置方法。
        interceptor.after(proxy,target,method,args);
        return result;
    }

    public static void main(String[] args) {
        HelloWorld proxy = (HelloWorld)new InterceptorJdkProxy(new HelloWorldimpl(),
                "com.training.pattern.Interceptor.MyInterceptor").createProxy();
        proxy.sayHelloWorld("zhangsan ");
    }

}
