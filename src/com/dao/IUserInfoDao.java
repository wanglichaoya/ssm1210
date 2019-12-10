package com.dao;

import com.po.ProvincePo;
import com.po.RolePo;
import com.po.UserInfo;
import com.po.UserRole;

import java.util.List;

public interface IUserInfoDao {
    List<UserInfo> queryUser(UserInfo userInfo);

    int insert(UserInfo userInfo);

    int editUser(UserInfo userInfo);

    int delUser(int id);

    int queryTotal();

    List<RolePo> selectUserRoles();

    int deleteUserRoleByUid(int userId);

    int insertUserRole(List<UserRole> intList);

    List<UserInfo> queryUserByKeyWord(UserInfo userInfo);

    List<ProvincePo> getProvince();

    /**
     * 根据用户id，查询用户 都有哪些角色
     **/
    List<UserRole> queryUsersRoleById(int uId);

    /**
     * 根据用户id，查询用户 详细信息
     **/
    /*List<UserInfo> findUserById(int uid);*/
    UserInfo findUserById(int uid);
}
