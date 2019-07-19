package com.training.pattern.Interceptor;

import com.training.pattern.proxy.HelloWorld;
import com.training.pattern.proxy.HelloWorldimpl;

/**
 * 责任链模式
 * 通过多个拦截器实现责任链
 * 请假条  ---》 项目经理 --- 》 部门经理---》 人事
 * 使用代理1  -- > 代理1代理代理2  --- 》 代理2去代理代理3
 * 结果如下：
 * 【拦截器3 】 的before 方法
 * 【拦截器2 】 的before 方法
 * 【拦截器1 】 的before 方法
 * ................
 * 【拦截器1 】 after 方法
 * 【拦截器2 】 after 方法
 * 【拦截器3 】 after 方法
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {

        HelloWorld proxy1 = (HelloWorld)new InterceptorJdkProxy(new HelloWorldimpl(),
                "com.training.pattern.Interceptor.chain.MyInterceptor1").createProxy();
        HelloWorld proxy2 = (HelloWorld)new InterceptorJdkProxy(proxy1,
                "com.training.pattern.Interceptor.chain.MyInterceptor2").createProxy();
        HelloWorld proxy3 = (HelloWorld)new InterceptorJdkProxy(proxy2,
                "com.training.pattern.Interceptor.chain.MyInterceptor3").createProxy();

        proxy3.sayHelloWorld("zhangsan");
    }
}
