package com.training.myaop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


//  集成  aspect J   代码
//  切面 == 通知  + 切入点
@Aspect
@Component
public class GreetingAspect {
    @Around(value = "execution(* com.training.myaop.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws  Throwable{
        before();
        Object result = joinPoint.proceed();
        after();
        return result;
    }

    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }
}
