package com.service;

import com.po.LoginLog;
import org.springframework.stereotype.Service;

public interface ILoginLogService {

    int insert(LoginLog loginLog);
    int insert2();
}
