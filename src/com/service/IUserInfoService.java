package com.service;

import com.po.ProvincePo;
import com.po.RolePo;
import com.po.UserInfo;
import com.po.UserRole;

import java.util.List;

public interface IUserInfoService {
    List<UserInfo> queryUser(UserInfo userInfo);

    int insert(UserInfo userInfo);

    int editUser(UserInfo userInfo);

    int delUser(int id);

    int queryTotal();

    /**
     * 查询用户角色
     * **/
    List<RolePo> selectUserRoles();

    /**
     * 删除用户的角色
     * **/
    int deleteUserRoleByUid(int userId);
    /**
     * 插入用户的角色
     * **/
    int insertUserRole(List<UserRole> intList);

    /**
     * 关键字查询
     * **/
    List<UserInfo> queryUserByKeyWord(UserInfo userInfo);

    List<ProvincePo> getProvince();

    /**
     * 根据用户id，查询用户 都有哪些角色
     * **/
    List<UserRole> queryUsersRoleById(int uId);
    /**
     * 根据用户id，查询用户 详细信息
     * **/
    /*List<UserInfo> findUserById(int uid);*/
    UserInfo findUserById(int uid);
}
