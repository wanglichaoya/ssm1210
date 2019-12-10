package com.po;

import java.util.PrimitiveIterator;

/**
 * describe: 菜单实体类
 *
 * @author 王立朝
 * @date 2019/09/16
 */
public class MenuPo {

    //父级菜单id
    private int MPid;
    //菜单名称
    private String MName;
    //菜单路径
    private String MUrl;
    //菜单个数
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMPid() {
        return MPid;
    }

    public void setMPid(int MPid) {
        this.MPid = MPid;
    }

    public String getMName() {
        return MName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public String getMUrl() {
        return MUrl;
    }

    public void setMUrl(String MUrl) {
        this.MUrl = MUrl;
    }
}
