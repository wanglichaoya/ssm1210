package com.log.factory;

import com.po.LoginLog;
import com.state.LogSucceed;
import com.state.LogType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/09/20
 */

public class LogFactory {


    /**
     * 创建登录日志
     **/
    public static LoginLog createLoginLog(LogType logType, int userId) {
      /*  ApplicationContext context = new ClassPathXmlApplicationContext("application-service.xml");*/
       /* LoginLog loginLog = (LoginLog) context.getBean("loginLog");*/
        LoginLog loginLog = new LoginLog();

        loginLog.setLogName(logType.getMessage());
        loginLog.setUserId(userId);
        loginLog.setCreateTime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIpAddress("123333333");
        loginLog.setMessage("");
        return loginLog;
    }


}
