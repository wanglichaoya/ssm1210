package com.controller.login;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.log.LogManager;
import com.log.factory.LogFactory;
import com.log.factory.LogTaskFactory;
import com.po.LoginLog;
import com.po.TreeNode;
import com.po.UserInfo;
import com.service.ILoginLogService;
import com.service.ILoginService;
import com.service.IResourceTreeService;
import com.service.IUserInfoService;
import com.state.LogSucceed;
import com.state.LogType;
import com.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequestMapping("/loginController")
@Controller
public class LoginController {

    @Resource
    private ILoginService loginService;
    @Resource
    private IUserInfoService iuserInfoService;
    @Resource
    private IResourceTreeService iResourceTreeService;
    @Resource
    private ILoginLogService iLoginLogService;


 /*

    @Autowired
    private LogType logType;
    @Resource
    private LogFactory logFactory;*/

    @RequestMapping("/login")
    public String login(UserInfo userInfo, Model model, HttpServletRequest request) {
        String pass = userInfo.getPassword();
        String md5Password = Md5Util.getMD5(pass);
        userInfo.setPassword(md5Password);
        UserInfo userInfo1 = loginService.login(userInfo);
        List<UserInfo> userRoleList = null;
        if (null != userInfo1) {
            userRoleList = loginService.getUserRole(userInfo1);
            if (userRoleList.size() > 0) {
                //查询该角色是否有可以访问的资源
                List<TreeNode> treeNodesList = loginService.selectResByName(userInfo.getUserName());
                if (treeNodesList.size() > 0) {
                    model.addAttribute("success", userInfo.getUserName());
                    //插入登录成功日志
                    /*LogManager.me().executeLog(LogTaskFactory.loginLog(userInfo1.getId()));*/

                    /*LoginLog loginLog = logFactory.createLoginLog(LogType.LOGIN,userInfo1.getId());*/
                    /*loginLog.setLogName("登录日志");
                    loginLog.setUserId(userInfo1.getId());
                    loginLog.setCreateTime(new Date());
                    loginLog.setSucceed("成功");
                    loginLog.setIpAddress("123333333");
                    loginLog.setMessage("");*/

                    iLoginLogService.insert2();

                    /*session.setMaxInactiveInterval(120);*/

                    request.getSession().setAttribute("NowUserName", userInfo.getUserName());
                    request.getSession().setMaxInactiveInterval(120);
                    if (treeNodesList.size() == 1) {
                        model.addAttribute("userRoleName", userRoleList.get(0).getRoleName());
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int j = 0; j < userRoleList.size(); j++) {
                            stringBuffer.append(userRoleList.get(j).getRoleName());
                        }
                        model.addAttribute("userRoleName", stringBuffer);

                    }
                    return "index";
                } else {
                    model.addAttribute("error", "该角色没有配置可访问的资源！请联系管理员！");
                    return "../../login3";
                }
            } else {
                model.addAttribute("error", "您没有权限登陆！请联系管理员！");
                return "../../login3";
            }
        } else {
            model.addAttribute("error", "用户名或密码错误!");
            return "../../login3";
        }
       /* if ("" != userName && "" != password) {
            model.addAttribute("success", userName);
            return "layout";
        } else {
            model.addAttribute("error", "用户名或密码错误!");
            return "login_error";
        }*/

    }


    //从session中获取用户名，然后根据用户名查找相应的资源
    @RequestMapping("/loadTreeByRole")
    @ResponseBody
    public JSONArray loadTreeByRole(Model model, HttpServletRequest request) {
        String username = request.getSession().getAttribute("NowUserName").toString();

        //List<TreeNode> treeNodesList = iResourceTreeService.loadTreeByRoleParent();
        List<TreeNode> treeNodesList = loginService.selectResByName(username);
        if (treeNodesList.size() > 0) {
            List<TreeNode> treeNodesListParent = new ArrayList<TreeNode>();
            for (int i = 0; i < treeNodesList.size(); i++) {
                TreeNode treeNodeParent = new TreeNode();

                System.out.printf("父类的url是： " + treeNodesList.get(i).getUrl());

                treeNodeParent.setmId(treeNodesList.get(i).getmId());
                treeNodeParent.setText(treeNodesList.get(i).getText());
                treeNodeParent.setState(treeNodesList.get(i).getState());
                treeNodeParent.setUrl(treeNodesList.get(i).getUrl());

                treeNodesListParent.add(treeNodeParent);

                //根据父类名称查询子类
                Map<String, Object> objectMap = new HashMap<String, Object>();
                objectMap.put("text", treeNodesList.get(i).getText());
                objectMap.put("username", username);
                //List<TreeNode> treeNodesListChildList = iResourceTreeService.loadTreeByRoleChild(objectMap);
                List<TreeNode> treeNodesListChildList = loginService.loadTreeByRoleChild(objectMap);
                List<TreeNode> treeNodeListAttribute = new ArrayList<TreeNode>();
                for (TreeNode treeNodeChilds : treeNodesListChildList) {
                    TreeNode treeNodeChild = new TreeNode();
                    treeNodeChild.setmId(treeNodeChilds.getmId());
                    treeNodeChild.setmPid(treeNodeChilds.getmPid());
                    treeNodeChild.setText(treeNodeChilds.getText());
                    treeNodeChild.setState(treeNodeChilds.getState());
                    treeNodeChild.setUrl(treeNodeChilds.getUrl());
                    Map<String, Object> urlMap = new HashMap<String, Object>();
                    urlMap.put("url", treeNodeChilds.getUrl());
                    treeNodeChild.setAttributes(urlMap);
                    treeNodeListAttribute.add(treeNodeChild);
                }

                treeNodeParent.setChildren(treeNodeListAttribute);

            }
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(treeNodesListParent);
            System.out.printf("json串为： " + jsonArray.toJSONString());
            return jsonArray;
        } else {


        }
        return null;

    }


}
