package com.service;

import com.po.MenuPo;
import com.po.common.CommboxPo;

import java.util.List;

public interface ISystemService {

    /**
     * 查询父级菜单
     **/
    List<CommboxPo> getParentMenu();

    /**
     * 新增菜单
     **/
    int addMenu(MenuPo menuPo);

    /**
     * 验证是否有同一个父级菜单下有多个重名的菜单
     **/
    List<MenuPo> check();
}
