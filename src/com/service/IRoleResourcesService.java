package com.service;

import com.po.RolePo;
import com.po.TreeNode;

import java.util.List;
import java.util.Map;

public interface IRoleResourcesService {

    int queryRolesTotal();

    List<RolePo> queryRoles();

    int addRoles(RolePo rolePo);

    int delRoles(RolePo rolePo);

    int editRoles(RolePo rolePo);

    /**
     * 查找父级列表
     **/
    List<TreeNode> selectParentList();

    /**
     * 查找父级列表对应的子级列表
     **/
    List<TreeNode> selectChildList(Map<String, Object> map);

    int updateRolesResources(Map<String, Object> map);

    /**
     * 根据角色 id 查找对应的父级菜单
     **/
    List<TreeNode> queryResourcesByRole(Map<String, Object> paramMap);

    List<TreeNode> queryResourcesByRoleChild(Map<String, Object> objectMap);

    /**
     * 查询所有的资源
     **/
    List<TreeNode> queryAllResources();

    /**
     * 查询所有的资源 的子菜单
     **/
    List<TreeNode> queryAllResourcesChild(Map<String, Object> objectMap);

    int delRolesResources(int roleId);

    int insertRolesResources(int roleId, List list);

    /**
     * 根据勾选的 资源id，找到 父类的id,然后插入到角色资源表中
     **/
    List<TreeNode> selectParentRoleResId(List list);

    /**
     * 插入父类 资源id到角色资源表中
     **/
    int insertRolesParentResources(int roleId, List<TreeNode> list);

    /**
     * 设置角色-
     * 查询 角色 对应的所有可以访问的资源
     **/
    List<RolePo> queryResByRole(int roleId);
}
