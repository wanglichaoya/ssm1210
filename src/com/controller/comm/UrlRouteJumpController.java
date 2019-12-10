package com.controller.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/urlRouteJump")
@Controller
public class UrlRouteJumpController {
    @Autowired
    private HttpSession session;
    //跳转到 layout页面
    @RequestMapping("/toLayout")
    public String toLayout() {

        return "index";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        //清除session 会话
        request.getSession().removeAttribute("NowUserName");
        //跳转到 登录页面
        return "../../login3";

    }

    //跳转到 密码管理页面
    @RequestMapping("/toPwdPage")
    public String toPwdPage() {
        return "passwordManager";
    }

    //跳转到 普通会员页面
    @RequestMapping("/toPutongVipPage")
    public String toPutongVipPage() {
        return "putongVIP";
    }

    //跳转到 用户管理页面页面
    @RequestMapping("/toUserMagPage")
    public String toUserMagPage() {
        return "userManager";
    }

    //跳转到医院管理页面
    @RequestMapping("/toHospitalMagPage")
    public String toHospitalMagPage(){
        return "hospitalManager";
    }
    //跳转到诊所页面
    //return "../hospital/clinicManger";表示： 跳转到WEB-INF 下面的jsp 的上一级，然后到 hospital/clinicManager.jsp
    @RequestMapping("/toClinicPage")
    public String toClinicPage(){
        return "../hospital/clinicManager";
    }

    //跳转到资源管理页面
    @RequestMapping("/toResourcesPage")
    public String toResourcesPage(){
        return "../roleresourcesmag/resourcesManager";
    }
    //跳转到角色管理页面
    @RequestMapping("/toRolesManager")
    public String toRolesManager(){
        return "../roleresourcesmag/rolesManager";
    }
    @RequestMapping("/toTestAspect")
    public String toTestAspect(){
        return "testAspect";
    }
    //跳转到字体滚动的页面
    @RequestMapping("/toFontRun")
    public String toFontRun(){
        return "/other/fontRun";
    }
    //跳转到购物车页面
    @RequestMapping("/toShoppingCar")
    public String toShoppingCar(){
        return "/ecommerce/shoppingCar";
    }
    //跳转到菜单管理页面
    @RequestMapping("/toMenuManager")
    public String toMenuManagerPage(){
        return "/system/MenuManager";
    }
    //跳转到登录日志页面
    @RequestMapping("/toLoginLog")
    public String toLoginLog(){
        return "/loginlogmanager/loginManager";
    }

}
