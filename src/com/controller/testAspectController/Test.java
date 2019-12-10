package com.controller.testAspectController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/08/13
 */
@Controller
public class Test {

    @RequestMapping("/addUser")
    public String addUser(){
        System.out.println("测试添加,之前会插入日志中！");
        return "";
    }

}
