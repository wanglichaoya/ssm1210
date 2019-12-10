package com.service.impl;

import com.dao.IUserInfoDao;
import com.po.ProvincePo;
import com.po.RolePo;
import com.po.UserInfo;
import com.po.UserRole;
import com.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDao iUserInfoDao;
    @Override
    public List<UserInfo> queryUser(UserInfo userInfo) {
        //当前第几页
        int page = userInfo.getPage();
        //每页显示多少条
        int rows = userInfo.getRows();

        //判断可以分成多少页
        int total = queryTotal();
        int start = 0;
        if(page == 1){
            start = (page-1)*rows ;
        }else{
            start = (page-1)*rows ;
        }
        int end = 0;
        end = page*rows;
        userInfo.setStart(start);
        userInfo.setEnd(end);

 /*       int totalPage = 0;
        int start = 0;

        if(total % rows == 0){
            totalPage = total / rows;
        }else{
            totalPage = total / rows + 1;
        }

        if(page == totalPage){
            start = (page-1)*rows+1 ;
        }else{
            start = (page-1)*rows  ;
        }*/


//        int end = page * rows;


        System.out.println(" start " + start + " end" + end);
        List<UserInfo> userInfoList = iUserInfoDao.queryUser(userInfo);
        return userInfoList;
    }

    @Override
    public int insert(UserInfo userInfo) {
        int result = iUserInfoDao.insert(userInfo);
        return result;
    }

    @Override
    public int editUser(UserInfo userInfo) {
        int result = iUserInfoDao.editUser(userInfo);
        return result;
    }

    @Override
    public int delUser(int id) {
        int result = iUserInfoDao.delUser(id);
        return result;
    }

    @Override
    public int queryTotal() {
        return iUserInfoDao.queryTotal();
    }

    /**
     * 查询用户角色
     **/
    @Override
    public List<RolePo> selectUserRoles() {
        return iUserInfoDao.selectUserRoles();
    }

    /**
     * 删除用户的角色
     *
     * @param roleId
     **/
    @Override
    public int deleteUserRoleByUid(int userId) {
        return iUserInfoDao.deleteUserRoleByUid(userId);
    }

    /**
     * 插入用户的角色
     *
     * @param intList
     **/
    @Override
    public int insertUserRole(List<UserRole> intList) {
        return iUserInfoDao.insertUserRole(intList);
    }

    /**
     * 关键字查询
     *
     * @param userInfo
     **/
    @Override
    public List<UserInfo> queryUserByKeyWord(UserInfo userInfo) {
        return iUserInfoDao.queryUserByKeyWord(userInfo);
    }

    @Override
    public List<ProvincePo> getProvince() {
        return iUserInfoDao.getProvince();
    }

    /**
     * 根据用户id，查询用户 都有哪些角色
     *
     * @param uId
     **/
    @Override
    public List<UserRole> queryUsersRoleById(int uId) {
        return iUserInfoDao.queryUsersRoleById(uId);
    }

    /**
     * 根据用户id，查询用户 详细信息
     *
     * @param uid
     **/
    @Override
  /*  public List<UserInfo> findUserById(int uid) {
        return iUserInfoDao.findUserById(uid);
    }*/
    public UserInfo findUserById(int uid) {
        return iUserInfoDao.findUserById(uid);
    }


}
