package com.dao;

import com.po.RolePo;
import com.po.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleResouresDao {
    public int queryRolesTotal();

    public List<RolePo> queryRoles();

    int addRoles(RolePo rolePo);

    int delRoles(RolePo rolePo);

    int editRoles(RolePo rolePo);

    List<TreeNode> selectParentList();

    /**
     * 查找父级菜单对应的子级列表
     **/
    List<TreeNode> selectChildList(Map<String, Object> map);

    int updateRolesResources(Map<String, Object> map);

    /**
     * 根据角色id 查找父级菜单
     **/
    List<TreeNode> queryResourcesByRole(Map<String, Object> paramMap);

    List<TreeNode> queryResourcesByRoleChild(Map<String, Object> objectMap);

    List<TreeNode> queryAllResources();

    List<TreeNode> queryAllResourcesChild(Map<String, Object> objectMap);

    int delRolesResources(@Param("roleId") int roleId);

    int insertRolesResources(@Param("roleId") int roleId, @Param("list") List list);

    /**
     * 根据勾选的 资源id，找到 父类的id,然后插入到角色资源表中
     */
    List<TreeNode> selectParentRoleResId(@Param("list") List list);

    /**
     * 插入父类 资源id到角色资源表中
     **/
    int insertRolesParentResources(@Param("roleId") int roleId, @Param("list") List<TreeNode> list);

    /**
     * 设置角色-
     * 查询 角色 对应的所有可以访问的资源
     **/
    List<RolePo> queryResByRole(int roleId);
}
