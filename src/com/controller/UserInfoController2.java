package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.po.*;
import com.service.ILoginService;
import com.service.IResourceTreeService;
import com.service.IUserInfoService;
import com.util.Md5Util;
import com.util.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequestMapping("/user2")
@Controller
public class UserInfoController2 {

    @Resource
    private IUserInfoService iuserInfoService;
    @Resource
    private IResourceTreeService iResourceTreeService;

    @Autowired
    private HttpSession session;
    @Resource
    private ILoginService loginService;

    private UserInfo userInfo;

    private TreeNode treeNode;

    @RequestMapping("/queryUserInfo")
    public ModelAndView queryUserInfo(ModelAndView view) {
        Map<String, UserInfo> userInfoMap = new HashMap<String, UserInfo>();
        view = new ModelAndView();
        view.addObject("user", "hasdfsd");
//        view.setViewName("/user/userInfo");
        view.setViewName("index.jsp");
        return view;
    }

    @RequestMapping("/queryUserInfo2")
    @ResponseBody
    public Map<String, Object> queryUserInfo2(UserInfo userInfo, HttpServletRequest request) {
        String keyWord = request.getParameter("keyword");
        List<UserInfo> userInfoList = null;
        int total = 0;
        if ("".equals(keyWord) || null == keyWord) {
            total = iuserInfoService.queryTotal();
            userInfoList = iuserInfoService.queryUser(userInfo);
        } else {
            userInfo.setKeyWord(keyWord);
            userInfoList = iuserInfoService.queryUserByKeyWord(userInfo);
            total = userInfoList.size();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", userInfoList);
        map.put("total", total);
        return map;
    }


    /*@RequestMapping("/findUserById")
    @ResponseBody
    public JSONArray findUserById(HttpServletRequest request) {
        String strUid = request.getParameter("uid");
        int intUid = Integer.parseInt(strUid);
        List<UserInfo> userInfoList = null;
        int total = 0;
        userInfoList = iuserInfoService.findUserById(intUid);
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(userInfoList);
        return jsonArray;
    }*/

    @RequestMapping("/findUserById")
    @ResponseBody
    public UserInfo findUserById(HttpServletRequest request) {
        String strUid = request.getParameter("uid");
        int intUid = Integer.parseInt(strUid);
        List<UserInfo> userInfoList = null;
        int total = 0;
        UserInfo userInfo = iuserInfoService.findUserById(intUid);

        return userInfo;
    }

    /**对用户名进行唯一性验证**/
    @RequestMapping("/checkUserName")
    public String checkUserName(HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        String userName = request.getParameter("userName");
        if("张三".equals(userName) && !"".equals(userName)){
            jsonObject.put("error", "checkError");
            ResponseUtil.Write(response, jsonObject);
        }else{
            jsonObject.put("success", "checkSucc");
            ResponseUtil.Write(response, jsonObject);
        }
        return null;

    }

    /**
     * 新增用户
     **/
    @RequestMapping("/addUser")
    public String addUser(UserInfo userInfo, HttpServletResponse response) {
        int page = userInfo.getPage();
        int rows = userInfo.getRows();
        String pass = userInfo.getPassword();
        String md5Password = Md5Util.getMD5(pass);
        userInfo.setPassword(md5Password);
        int result = iuserInfoService.insert(userInfo);
        JSONObject resultJSONObject = new JSONObject();
        System.out.println("新增结果为 " + result);
        JSONObject jsonObject = new JSONObject();
        if (result > 0) {
            jsonObject.put("success", "addSucc");
            ResponseUtil.Write(response, jsonObject);
        } else {
            jsonObject.put("error", "addError");
            ResponseUtil.Write(response, jsonObject);
        }
        return null;
    }



    /**
     * 获取省份列表
     **/
    @RequestMapping("/getProvince")
    @ResponseBody
    public JSONArray getProvince() {
        JSONObject jsonObject = new JSONObject();
        List<ProvincePo> provincePos = new ArrayList<ProvincePo>();
        /* ProvincePo provincePo = new ProvincePo();*/
    /*    provincePo.setId(1);
        provincePo.setText("河南");

        ProvincePo provincePo2 = new ProvincePo();
        provincePo2.setId(2);
        provincePo2.setText("安徽");

        provincePos.add(provincePo);
        provincePos.add(provincePo2);*/

        List<ProvincePo> provincePos1 = iuserInfoService.getProvince();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(provincePos1);
        return jsonArray;
    }


    /**
     * 修改用户
     **/
    @RequestMapping("/editUser")
    public String editUser(UserInfo userInfo, HttpServletResponse response) {
        String pass = userInfo.getPassword();
        String md5Password = Md5Util.getMD5(pass);
        userInfo.setPassword(md5Password);
        int result = iuserInfoService.editUser(userInfo);
        System.out.println("更新结果为 " + result);
        JSONObject jsonObject = new JSONObject();
        if (result > 0) {
            jsonObject.put("success", "editSucc");
            ResponseUtil.Write(response, jsonObject);
        } else {
            jsonObject.put("error", "editError");
            ResponseUtil.Write(response, jsonObject);
        }
        return null;
    }

    /**
     * 设置用户角色
     **/
    @RequestMapping("/setUserRole2")
    public String setUserRole2(HttpServletRequest request, HttpServletResponse response) {
        String star = "[";
        String end = "]";
        String josnMap = request.getParameter("map").toString();
        String json = star + josnMap + end;
        String row = request.getParameter("row");
        int userId = Integer.parseInt(row);
        List<Map> meterList = JSONArray.parseArray(json.toString(), Map.class);
        for (int i = 0; i < meterList.size(); i++) {
            System.out.println("list 的值为： " + meterList.get(0).toString());

        }
        //ArrayList userList = JSON.parseObject(josnMap, new TypeReference<ArrayList>() {});
        // System.out.println("条数为： " + userList.size() + "用户id 为：" + row + " dd==>" + userList.get(0));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", "editError");
        ResponseUtil.Write(response, jsonObject);
        return null;
    }

    /**
     * 设置用户角色
     **/
    @RequestMapping("/setUserRole")
    public String setUserRole(HttpServletRequest request, HttpServletResponse response) {
        String[] nodes = request.getParameterValues("map");
        String row = request.getParameter("row").toString();
        //用户id
        int userId = Integer.parseInt(row);
        List<String> asList = Arrays.asList(nodes);
        List<UserRole> intList = new ArrayList<UserRole>();
        UserRole ur = new UserRole();
        for (int i = 0; i < asList.size(); i++) {
            System.out.println("list 的值为===》" + Integer.parseInt(asList.get(i)));

            ur.setRid(Integer.parseInt(asList.get(i)));
            ur.setUid(userId);
            intList.add(ur);
        }
        //删除 用户的角色
        int result = iuserInfoService.deleteUserRoleByUid(userId);
        JSONObject jsonObject = new JSONObject();
        if (result > 0) {
            //插入勾选的角色
            // int resultUr = iuserInfoService.insertUserRole(intList);
           /* int resultUr = iuserInfoService.insertUserRole(ur);
            if(resultUr > 0){
                jsonObject.put("success", "success");
            }else{
                jsonObject.put("error", "error");
            }*/

        } else {
            jsonObject.put("error", "error");
        }

        ResponseUtil.Write(response, jsonObject);
        return null;
    }

    /**
     * 设置用户角色3
     * **/

    /**
     * 设置用户角色
     **/
    @RequestMapping("/setUserRole3")
    public String setUserRole3(HttpServletRequest request, HttpServletResponse response) {
        String[] nodes = request.getParameterValues("map");
        String row = request.getParameter("row").toString();
        //用户id
        int userId = Integer.parseInt(row);
        List<String> asList = Arrays.asList(nodes);
        List<UserRole> intList = new ArrayList<UserRole>();
        for (int i = 0; i < asList.size(); i++) {
            System.out.println("list 的值为===》" + Integer.parseInt(asList.get(i)));
            UserRole ur = new UserRole();
            ur.setRid(Integer.parseInt(asList.get(i)));
            ur.setUid(userId);
            intList.add(ur);
        }
        //删除 用户的角色
        int result = iuserInfoService.deleteUserRoleByUid(userId);
        JSONObject jsonObject = new JSONObject();
        int resultUr = iuserInfoService.insertUserRole(intList);
        if (resultUr > 0) {
            jsonObject.put("success", "success");
        } else {
            jsonObject.put("error", "error");
        }

        ResponseUtil.Write(response, jsonObject);
        return null;
    }


    /**
     * 删除用户
     **/
    @RequestMapping("/delUser")
    public String delUser(int id, HttpServletResponse response) {
        int result = iuserInfoService.delUser(id);
        System.out.println("删除结果为 " + result);
        JSONObject jsonObject = new JSONObject();
        if (result > 0) {
            jsonObject.put("success", true);
            ResponseUtil.Write(response, jsonObject);
        }
        return null;
       /* if (result > 0) {

            return "redirect:/user2/queryUserInfo2.action";
            // return ((JSONObject) json).put("success",true).toString();
        } else {
            // return ((JSONObject) json).put("error",false).toString();
            return "redirect:/user2/queryUserInfo2.action";
        }*/

    }

    @RequestMapping("/shiroLogin")
    public void shiroLogin(UserInfo userInfo) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassword());
        try {
            user.login(token);
            System.out.println("登录成功 = " + token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败 = ");
        }

    }

    @RequestMapping("/login")
    public String login(UserInfo userInfo, Model model) {
        String userName = userInfo.getUserName();
        String password = userInfo.getPassword();

        if ("" != userName && "" != password) {
            model.addAttribute("success", userName);
            return "layout";
        } else {
            model.addAttribute("error", "用户名或密码错误!");
            return "login_error";
        }

    }


    @RequestMapping("/error")
    public String error() throws Exception {
        throw new Exception();
    }

    @RequestMapping("/loadTreeByRole")
    @ResponseBody
    public JSONArray loadTreeByRole() {
        List<TreeNode> treeNodesList = iResourceTreeService.loadTreeByRoleParent();
        List<TreeNode> treeNodesListParent = new ArrayList<TreeNode>();
        for (int i = 0; i < treeNodesList.size(); i++) {
            TreeNode treeNodeParent = new TreeNode();
            String parentName = treeNodesList.get(i).getText();
            String parentState = treeNodesList.get(i).getState();
            System.out.printf("父类的url是： " + treeNodesList.get(i).getUrl());
            treeNodeParent.setText(parentName);
            treeNodeParent.setState(parentState);

            treeNodesListParent.add(treeNodeParent);

            //根据父类名称查询子类
            Map<String, Object> objectMap = new HashMap<String, Object>();
            objectMap.put("text", parentName);
            List<TreeNode> treeNodesListChildList = iResourceTreeService.loadTreeByRoleChild(objectMap);
            List<TreeNode> treeNodeListAttribute = new ArrayList<TreeNode>();
            for (TreeNode treeNodeChilds : treeNodesListChildList) {
                TreeNode treeNodeChild = new TreeNode();
                treeNodeChild.setText(treeNodeChilds.getText());
                treeNodeChild.setState(treeNodeChilds.getState());
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
    }

    @RequestMapping("/toMenu2")
    public String toMenu2() {
        return "tomenu2";
    }


    @RequestMapping("/loadTreeInit")
    @ResponseBody
    public JSONArray loadTreeInit() {
        TreeNode treeNode = new TreeNode();
        treeNode.setText("菜单");
        treeNode.setState("open");
        List<TreeNode> treeNodesList = new ArrayList<TreeNode>();
        treeNodesList.add(treeNode);

        TreeNode treeNode1 = new TreeNode();
        treeNode1.setText("子目录");
        treeNode1.setState("open");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", "../../user2/toLayout.action\"");
        treeNode1.setAttributes(map);

        TreeNode treeNode2 = new TreeNode();
        treeNode2.setText("子目录2");
        treeNode2.setState("open");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("url", "../../user2/toMenu2.action\"");
        treeNode2.setAttributes(map2);

        List<TreeNode> treeNode1List = new ArrayList<TreeNode>();
        treeNode1List.add(treeNode1);
        treeNode1List.add(treeNode2);

        treeNode.setChildren(treeNode1List);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(treeNodesList);
        return jsonArray;
    }


    //加载用户的角色
    @RequestMapping("/loadUserRoles")
    @ResponseBody
    public JSONArray loadUserRoles() {

        List<RolePo> roleList = iuserInfoService.selectUserRoles();
        List<RolePo> roleListParent = new ArrayList<RolePo>();
        for (int i = 0; i < roleList.size(); i++) {
            RolePo roleParent = new RolePo();
            roleParent.setrId(roleList.get(i).getrId());
            roleParent.setText(roleList.get(i).getText());
            roleListParent.add(roleParent);
        }
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(roleListParent);
        System.out.printf("json串为： " + jsonArray.toJSONString());

        return jsonArray;
    }

    /**
     * 根据用户id 查看用户当前的角色
     **/
    @RequestMapping("/queryUsersRoleById")
    @ResponseBody
    public JSONArray queryUsersRoleById(HttpServletRequest request) {
        String uidStr = request.getParameter("userId");
        int uId = Integer.parseInt(uidStr);
        List<UserRole> userRoleList = iuserInfoService.queryUsersRoleById(uId);
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(userRoleList);
        return jsonArray;
    }


    public static void main(String[] args) {
       /* TreePo treePo = new TreePo();
        treePo.setState("open");
        treePo.setText("菜单后台");
        List<TreePo> treePos = new ArrayList<TreePo>();
        treePos.add(treePo);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(treePos);
        System.out.println("jsonArray = " + jsonArray.toJSONString());*/

        TreeNode treeNode = new TreeNode();
        treeNode.setText("菜单");
        treeNode.setState("open");
        List<TreeNode> treeNodesList = new ArrayList<TreeNode>();
        treeNodesList.add(treeNode);

        TreeNode treeNode1 = new TreeNode();
        treeNode1.setText("子目录");
        treeNode1.setState("open");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", "../../user2/toLayout.action\"");
        treeNode1.setAttributes(map);

        TreeNode treeNode2 = new TreeNode();
        treeNode2.setText("子目录2");
        treeNode2.setState("open");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("url", "../../user2/toLayout.action\"");
        treeNode2.setAttributes(map2);

        List<TreeNode> treeNode1List = new ArrayList<TreeNode>();
        treeNode1List.add(treeNode1);
        treeNode1List.add(treeNode2);


        treeNode.setChildren(treeNode1List);


        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(treeNodesList);
        System.out.println("结果为：    " + jsonArray.toJSONString());
    }

}
