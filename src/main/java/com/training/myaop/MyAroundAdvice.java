package com.training.myaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @date 2019/8/14
 */
@Component
public class MyAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        return methodInvocation.proceed();
    }

    public static void main(String[] args) {
        System.out.println(MyAroundAdvice.class.getName());
        System.out.println(MyAroundAdvice.class.getSimpleName());
    }
}
