package com.log.factory;

import com.dao.LoginLogDao;
import com.log.LogManager;
import com.po.LoginLog;
import com.service.ILoginLogService;
import com.state.LogType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * describe:日志操作任务创建工厂
 *
 * @author 王立朝
 * @date 2019/09/20
 */
public class LogTaskFactory {

    @Resource
    private   ILoginLogService iLoginLogService;

    //记录 用户 的id 到日志里面
   /* public static TimerTask loginLog(int  userId){
        return new TimerTask() {
            @Override
            public void run() {
                System.out.println("-----开始插入---");
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN,userId);
                iLoginLogService.insert(loginLog);
                System.out.println("---插入成功----");
            }
        };
    }*/

    public void loginLog(int  userId){
        System.out.println("-----开始插入---");
        LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN,userId);
        iLoginLogService.insert(loginLog);
        System.out.println("---插入成功----");
    }
}
