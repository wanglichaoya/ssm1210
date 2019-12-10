package com.controller.LogManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * describe:  日志切面
 *
 * @author 王立朝
 * @date 2019/08/13
 */
@Aspect
@Component
public class LogAspect {



    //定义切点
   /* private final String Point_CUT = "execution(* com.controller.LogManager.*.*.*(..))";*/
    private final String Point_CUT = "execution(* com.controller.*.*.*(..))";
    @Pointcut(Point_CUT)
    public void pointCut() {

    }

    /**
     * 前置通知 ，插入日志
     **/

   /* @Before(value = "pointCut()")*/
    @Before("execution(* com.controller.comm.UrlRouteJumpController.logOut(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("===========前置通知开始==========");

    }

}
