package com.intercepter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/08/16
 */
@Aspect
@Component
public class LogAspectDemo {

    //声明该方法是一个前置通知：在目标方法开始之前执行
//    @Before("execution(* com.controller.*.*(..))")
    //@Before("execution(* com.*.*.*(..))")
    @Before("execution(* com.controller.UserInfoController2.*(..))")
    public void beforeMethod(JoinPoint joinpoint){
        // String methodName=joinpoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinpoint.getArgs());
        System.out.println("前置通知开始");
//        System.out.println("The method " + methodName+ " begins with"+args);
        System.out.println("目标方法名为:" + joinpoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinpoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinpoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinpoint.getSignature().getModifiers()));

        System.out.println("前置通知结束");
    }
}
