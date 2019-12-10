package com.controller.testAspectController;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/08/13
 */

public class LogManagerController implements MethodBeforeAdvice {



    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("测试相加");
    }


}
