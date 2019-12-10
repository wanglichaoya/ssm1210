package com.service.impl;

import com.dao.RoleResouresDao;
import com.po.RolePo;
import com.po.TreeNode;
import com.service.IRoleResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleResourcesServiceImpl implements IRoleResourcesService {

    @Autowired
    private RoleResouresDao roleResouresDao;
    @Override
    public int queryRolesTotal() {
        return roleResouresDao.queryRolesTotal();
    }

    @Override
    public List<RolePo> queryRoles() {
        return roleResouresDao.queryRoles();
    }

    @Override
    public int addRoles(RolePo rolePo) {
        return roleResouresDao.addRoles(rolePo);
    }

    @Override
    public int delRoles(RolePo rolePo) {
        return roleResouresDao.delRoles(rolePo);
    }

    @Override
    public int editRoles(RolePo rolePo) {
        return roleResouresDao.editRoles(rolePo);
    }

    /**
     * 查找父级列表
     **/
    @Override
    public List<TreeNode> selectParentList() {
        return roleResouresDao.selectParentList();
    }

    /**
     * 查找父级列表对应的子级列表
     *
     * @param objectMap
     **/
    @Override
    public List<TreeNode> selectChildList(Map<String, Object> map) {
        return roleResouresDao.selectChildList(map);
    }

    @Override
    public int updateRolesResources(Map<String,Object> map) {
        return roleResouresDao.updateRolesResources(map);
    }

    /**
     * 根据角色 id 查找对应的父级菜单
     *
     * @param paramMap
     **/
    @Override
    public List<TreeNode> queryResourcesByRole(Map<String, Object> paramMap) {
        return roleResouresDao.queryResourcesByRole(paramMap);
    }

    @Override
    public List<TreeNode> queryResourcesByRoleChild(Map<String, Object> objectMap) {
        return roleResouresDao.queryResourcesByRoleChild(objectMap);
    }

    /**
     * 查询所有的资源
     **/
    @Override
    public List<TreeNode> queryAllResources() {
        return roleResouresDao.queryAllResources();
    }

    /**
     * 查询所有的资源 的子菜单
     *
     * @param objectMap
     **/
    @Override
    public List<TreeNode> queryAllResourcesChild(Map<String, Object> objectMap) {
        return roleResouresDao.queryAllResourcesChild(objectMap);
    }

    @Override
    public int delRolesResources(int roleId) {
        return roleResouresDao.delRolesResources(roleId);
    }

    @Override
    public int insertRolesResources(int roleId, List list) {
        return roleResouresDao.insertRolesResources(roleId,list);
    }

    /**
     * 根据勾选的 资源id，找到 父类的id,然后插入到角色资源表中
     *
     * @param list
     **/
    @Override
    public List<TreeNode> selectParentRoleResId(List list) {
        return roleResouresDao.selectParentRoleResId(list);
    }

    /**
     * 插入父类 资源id到角色资源表中
     *
     * @param roleId
     * @param listParent
     **/
    @Override
    public int insertRolesParentResources(int roleId, List<TreeNode> listParent) {
        return roleResouresDao.insertRolesParentResources(roleId, listParent);
    }

    /**
     * 设置角色-
     * 查询 角色 对应的所有可以访问的资源
     *
     * @param roleId
     */
    @Override
    public List<RolePo> queryResByRole(int roleId) {
        return roleResouresDao.queryResByRole(roleId);
    }


}
