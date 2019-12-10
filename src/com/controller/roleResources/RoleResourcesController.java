package com.controller.roleResources;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.po.RolePo;
import com.po.TreeNode;
import com.service.IRoleResourcesService;
import com.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * When I wrote this code, only God and I knew it, but now only God knows it.
 **/
@RequestMapping("/RoleRes")
@Controller
public class RoleResourcesController {

    @Resource
    private IRoleResourcesService iRoleResourcesService;

    @RequestMapping("/loadMenuItem2")
    @ResponseBody
    public JSONArray loadMenuItem2() {
        List<TreeNode> treeNodesList = new ArrayList<TreeNode>();

        TreeNode treeNode = new TreeNode();
        treeNode.setmId(1);
        treeNode.setText("父级菜单1");
        treeNode.setState("open");
        treeNode.setUrl("#");

        treeNodesList.add(treeNode);

        TreeNode treeNode2 = new TreeNode();
        treeNode2.setmId(3);
        treeNode2.setText("父级菜单2");
        treeNode2.setState("open");
        treeNode2.setUrl("#");

        treeNodesList.add(treeNode2);

        List<TreeNode> treeNodeChildList = new ArrayList<TreeNode>();
        TreeNode treeNodeChild = new TreeNode();
        treeNodeChild.setmId(2);
        treeNodeChild.setText("子目录1");
        treeNodeChild.setUrl("www.baiduc.com");
        treeNodeChild.setState("open");
        treeNodeChildList.add(treeNodeChild);

        List<TreeNode> treeNodeChildList2 = new ArrayList<TreeNode>();
        TreeNode treeNodeChild2 = new TreeNode();
        treeNodeChild2.setmId(4);
        treeNodeChild2.setText("子目录2");
        treeNodeChild2.setUrl("www.baiduc.com");
        treeNodeChild2.setState("open");
        treeNodeChildList2.add(treeNodeChild2);


        treeNode.setChildren(treeNodeChildList);
        treeNode2.setChildren(treeNodeChildList2);

        JSONArray jsonArray2 = (JSONArray) JSONArray.toJSON(treeNodesList);
        System.out.println("结果为：    " + jsonArray2.toJSONString());

        return jsonArray2;

    }


    /**
     * 加载资源 列表(资源管理)
     **/
    @RequestMapping("/loadMenuItem")
    @ResponseBody

    public JSONArray loadMenuItem() {

        //1、第一步：先查找父级列表
        List<TreeNode> parentTreeNodeList = iRoleResourcesService.selectParentList();
        List<TreeNode> treeNodeParList = new ArrayList<TreeNode>();
        for (int i = 0; i < parentTreeNodeList.size(); i++) {
            TreeNode treeNode = new TreeNode();
            treeNode.setmId(parentTreeNodeList.get(i).getmId());
            treeNode.setText(parentTreeNodeList.get(i).getText());
            treeNode.setState(parentTreeNodeList.get(i).getState());
            treeNode.setUrl(parentTreeNodeList.get(i).getUrl());
            treeNodeParList.add(treeNode);
            //根据父类的id,查找子类列表
            Map<String, Object> objectMap = new HashMap<String, Object>();
            objectMap.put("mId", parentTreeNodeList.get(i).getmId());
            List<TreeNode> treeNodeChildList = iRoleResourcesService.selectChildList(objectMap);
            List<TreeNode> treeNodeChildList2 = new ArrayList<TreeNode>();
            for (int j = 0; j < treeNodeChildList.size(); j++) {
                TreeNode treeNodeChild = new TreeNode();
                treeNodeChild.setmId(treeNodeChildList.get(j).getmId());
                treeNodeChild.setText(treeNodeChildList.get(j).getText());
                treeNodeChild.setState(treeNodeChildList.get(j).getState());
                treeNodeChild.setUrl(treeNodeChildList.get(j).getUrl());
                treeNodeChildList2.add(treeNodeChild);

            }
            treeNode.setChildren(treeNodeChildList2);

        }



        /*List<TreeNode> treeNodesList = new ArrayList<TreeNode>();

        TreeNode treeNode = new TreeNode();
        treeNode.setmId(1);
        treeNode.setText("父级菜单1");
        treeNode.setState("open");
        treeNode.setUrl("#");

        treeNodesList.add(treeNode);

        TreeNode treeNode2 = new TreeNode();
        treeNode2.setmId(3);
        treeNode2.setText("父级菜单2");
        treeNode2.setState("open");
        treeNode2.setUrl("#");

        treeNodesList.add(treeNode2);

        List<TreeNode> treeNodeChildList = new ArrayList<TreeNode>();
        TreeNode treeNodeChild = new TreeNode();
        treeNodeChild.setmId(2);
        treeNodeChild.setText("子目录1");
        treeNodeChild.setUrl("www.baiduc.com");
        treeNodeChild.setState("open");
        treeNodeChildList.add(treeNodeChild);

        List<TreeNode> treeNodeChildList2 = new ArrayList<TreeNode>();
        TreeNode treeNodeChild2 = new TreeNode();
        treeNodeChild2.setmId(4);
        treeNodeChild2.setText("子目录2");
        treeNodeChild2.setUrl("www.baiduc.com");
        treeNodeChild2.setState("open");
        treeNodeChildList2.add(treeNodeChild2);


        treeNode.setChildren(treeNodeChildList);
        treeNode2.setChildren(treeNodeChildList2);*/

        JSONArray jsonArray2 = (JSONArray) JSONArray.toJSON(treeNodeParList);
        System.out.println("结果为：    " + jsonArray2.toJSONString());

        return jsonArray2;

    }

    //查询角色
    @RequestMapping("/queryRoles")
    @ResponseBody
    public Map<String, Object> queryRoles() {
        int total = iRoleResourcesService.queryRolesTotal();
        if (total > 0) {
            List<RolePo> rolePoList = iRoleResourcesService.queryRoles();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rows", rolePoList);
            map.put("total", total);
            return map;
        }
        return null;
    }

    //根据角色 查找角色对应的资源
    @RequestMapping("/queryRoleResources")
    @ResponseBody
    public JSONArray queryRoleResources(String params, HttpServletResponse response) {
        System.out.println("选择的角色的id为：" + params);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", Integer.parseInt(params));
        //根据角色名称查找角色对应的父级菜单
        List<TreeNode> treeNodesList = iRoleResourcesService.queryResourcesByRole(paramMap);
        List<TreeNode> treeNodesListParent = new ArrayList<TreeNode>();
        JSONObject jsonObject = new JSONObject();
        if (treeNodesList.size() > 0) {
            for (int i = 0; i < treeNodesList.size(); i++) {
                TreeNode treeNodeParent = new TreeNode();

                System.out.printf("父类的url是： " + treeNodesList.get(i).getUrl());
                treeNodeParent.setmPid(treeNodesList.get(i).getmPid());
                treeNodeParent.setmId(treeNodesList.get(i).getmId());
                treeNodeParent.setText(treeNodesList.get(i).getText());
                treeNodeParent.setState(treeNodesList.get(i).getState());
                treeNodeParent.setUrl(treeNodesList.get(i).getUrl());

                treeNodesListParent.add(treeNodeParent);

                //根据父类名称查询子类
                Map<String, Object> objectMap = new HashMap<String, Object>();
                //父级id
                objectMap.put("mId", treeNodesList.get(i).getmId());
                objectMap.put("roleId", Integer.parseInt(params));
                List<TreeNode> treeNodesListChildList = iRoleResourcesService.queryResourcesByRoleChild(objectMap);
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
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(treeNodesListParent);
            return jsonArray;
        } else {
            jsonObject.put("error", "roleResError");
            ResponseUtil.Write(response, jsonObject);
            return null;
        }
    }

    ;

    //新增角色
    @RequestMapping("/addRoles")
    public String addRoles(RolePo rolePo, HttpServletResponse response) {
        int result = iRoleResourcesService.addRoles(rolePo);
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

    //删除角色
    @RequestMapping("/delRoles")
    public String delRoles(RolePo rolePo, HttpServletResponse response) {
        int result = iRoleResourcesService.delRoles(rolePo);
        JSONObject resultJSONObject = new JSONObject();
        System.out.println("删除结果为 " + result);
        JSONObject jsonObject = new JSONObject();
        if (result > 0) {
            jsonObject.put("success", "delSucc");
            ResponseUtil.Write(response, jsonObject);
        } else {
            jsonObject.put("error", "delError");
            ResponseUtil.Write(response, jsonObject);
        }
        return null;
    }


    //更新角色
    @RequestMapping("/editRoles")
    public String editRoles(RolePo rolePo, HttpServletResponse response) {
        int result = iRoleResourcesService.editRoles(rolePo);
        JSONObject resultJSONObject = new JSONObject();
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
     * 给角色设置资源
     **/
    @RequestMapping("/setRoleResources")
    public String setRoleResources(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String josnMap2 = request.getParameter("map2").toString();

        String rowId = request.getParameter("rowId");
        int roleId = Integer.parseInt(rowId);
        String[] args = request.getParameterValues("root");
        if (null == args) {
            //说明当前的这个角色没有选择，那么就把这个角色对应的所有资源删除
            int result = iRoleResourcesService.delRolesResources(roleId);
            if (result > 0) {
                System.out.println("授权成功,角色对应的资源已清空");
                jsonObject.put("success", "roleResClearSucc");
                ResponseUtil.Write(response, jsonObject);
            }else{
                jsonObject.put("error", "authorizationError");
                ResponseUtil.Write(response, jsonObject);
            }
        } else {
            String one = args[0];
            System.out.println("参数为： " + args + "ddd " + one);
            List list = new ArrayList();
            for (int i = 0; i < args.length; i++) {
                list.add(Integer.parseInt(args[i]));
            }

            //先批量删除 角色 菜单的所有数据
            int result = iRoleResourcesService.delRolesResources(roleId);


            //然后再批量插入 角色菜单的数据
            //批量插入之前，先检查是否只勾选了大类的其中某一个，这时候，需要先把 勾选的这些资源的 父类id 先插入到 角色资源表中，然后
            //再执行下面的方法
            List<TreeNode> listParent = iRoleResourcesService.selectParentRoleResId(list);
            List listPar = new ArrayList();
            for (int i = 0; i < listParent.size(); i++) {
                listPar.add(listParent.get(i).getmPid());
            }
            //插入父类 资源id到角色资源表中
            int insertResParent = iRoleResourcesService.insertRolesParentResources(roleId, listPar);
            //插入子类的 资源id 到角色资源表
            int insertRes = iRoleResourcesService.insertRolesResources(roleId, list);

            if (insertRes > 0) {
                System.out.println("授权成功");
                jsonObject.put("success", "authorizationSucc");
                ResponseUtil.Write(response, jsonObject);
            } else {
                System.out.println("授权失败");
                jsonObject.put("error", "authorizationError");
                ResponseUtil.Write(response, jsonObject);
            }
        }


        return null;
    }

    public List<TreeNode> get() {
        List<TreeNode> treeNods = JSONObject.parseArray("", TreeNode.class);
        return JSONObject.parseArray("", TreeNode.class);
    }


    @RequestMapping("/queryAllResources")
    @ResponseBody
    public JSONArray queryAllResources(HttpServletResponse response) {

        //根据角色名称查找角色对应的父级菜单
        List<TreeNode> treeNodesList = iRoleResourcesService.queryAllResources();
        List<TreeNode> treeNodesListParent = new ArrayList<TreeNode>();
        JSONObject jsonObject = new JSONObject();
        if (treeNodesList.size() > 0) {
            for (int i = 0; i < treeNodesList.size(); i++) {
                TreeNode treeNodeParent = new TreeNode();

                System.out.printf("父类的url是： " + treeNodesList.get(i).getUrl());
                treeNodeParent.setmPid(treeNodesList.get(i).getmPid());
                treeNodeParent.setmId(treeNodesList.get(i).getmId());
                treeNodeParent.setText(treeNodesList.get(i).getText());
                treeNodeParent.setState(treeNodesList.get(i).getState());
                treeNodeParent.setUrl(treeNodesList.get(i).getUrl());

                treeNodesListParent.add(treeNodeParent);

                //根据父类名称查询子类
                Map<String, Object> objectMap = new HashMap<String, Object>();
                //父级id
                objectMap.put("mId", treeNodesList.get(i).getmId());
                // objectMap.put("roleId", Integer.parseInt(params));
                List<TreeNode> treeNodesListChildList = iRoleResourcesService.queryAllResourcesChild(objectMap);
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
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(treeNodesListParent);
            return jsonArray;
        } else {
            jsonObject.put("error", "roleResError");
            ResponseUtil.Write(response, jsonObject);
            return null;
        }
    }

    /**
     * 设置角色-
     * 查询 角色 对应的所有可以访问的资源
     **/
    @RequestMapping("/queryResByRole")
    @ResponseBody
    public JSONArray queryResByRole(HttpServletRequest request) {
        String roleIdStr = request.getParameter("roleId");
        int roleId = Integer.parseInt(roleIdStr);
        System.out.println("角色的id是： " + roleId);
        List<RolePo> roleResList = iRoleResourcesService.queryResByRole(roleId);
        for (int i = 0; i < roleResList.size(); i++) {
            System.out.println("角色对应的资源是：" + roleResList.get(i).getrId());
        }
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(roleResList);
        return jsonArray;
    }


    public static void main(String[] args) {
        List<TreeNode> treeNodesList = new ArrayList<TreeNode>();

        TreeNode treeNode = new TreeNode();
        treeNode.setmId(1);
        treeNode.setText("父级菜单1");
        treeNode.setState("open");
        treeNode.setUrl("#");

        treeNodesList.add(treeNode);

        TreeNode treeNode2 = new TreeNode();
        treeNode2.setmId(3);
        treeNode2.setText("父级菜单2");
        treeNode2.setState("open");
        treeNode2.setUrl("#");

        treeNodesList.add(treeNode);
        treeNodesList.add(treeNode2);

        List<TreeNode> treeNodeChildList = new ArrayList<TreeNode>();
        TreeNode treeNodeChild = new TreeNode();
        treeNodeChild.setmId(2);
        treeNodeChild.setText("子目录1");
        treeNodeChild.setUrl("www.baiduc.com");
        treeNodeChildList.add(treeNodeChild);

        List<TreeNode> treeNodeChildList2 = new ArrayList<TreeNode>();
        TreeNode treeNodeChild2 = new TreeNode();
        treeNodeChild2.setmId(4);
        treeNodeChild2.setText("子目录2");
        treeNodeChild2.setUrl("www.baiduc.com");
        treeNodeChildList2.add(treeNodeChild2);


        treeNode.setChildren(treeNodeChildList);
        treeNode2.setChildren(treeNodeChildList2);

        JSONArray jsonArray2 = (JSONArray) JSONArray.toJSON(treeNodesList);
        System.out.println("结果为：    " + jsonArray2.toJSONString());

       /* TreeNode treeNode1 = new TreeNode();
        treeNode1.setText("子目录");
        treeNode1.setState("open");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", "../../user2/toLayout.action\"");
        treeNode1.setAttributes(map);

        List<TreeNode> treeNode1List = new ArrayList<TreeNode>();
        treeNode1List.add(treeNode1);


        treeNode.setChildren(treeNode1List);
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(treeNodesList);
        System.out.println("结果为：    " + jsonArray.toJSONString());*/
    }

}
