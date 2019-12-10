package com.service.impl;

import com.dao.LoginLogDao;
import com.po.LoginLog;
import com.po.TreeNode;
import com.po.UserInfo;
import com.service.ILoginLogService;
import com.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/09/20
 */

@Service
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private  LoginLogDao loginLogDao;

    @Override
    public int insert(LoginLog loginLog) {
        return loginLogDao.insert(loginLog);
    }
    public int insert2() {
        return loginLogDao.insert2();
    }
}
