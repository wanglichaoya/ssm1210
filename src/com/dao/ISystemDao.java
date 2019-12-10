package com.dao;

import com.po.MenuPo;
import com.po.common.CommboxPo;

import java.util.List;

public interface ISystemDao {
    List<CommboxPo> getParentMenu();

    int addMenu(MenuPo menuPo);

    List<MenuPo> check();
}
