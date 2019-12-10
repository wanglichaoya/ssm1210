package com.service.impl;

import com.dao.ISystemDao;
import com.po.MenuPo;
import com.po.common.CommboxPo;
import com.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/09/16
 */
@Service
public class SystemServiceImpl implements ISystemService {

    @Autowired
    private ISystemDao systemDao;

    /**
     * 查询父级菜单
     **/
    @Override
    public List<CommboxPo> getParentMenu() {
        return systemDao.getParentMenu();
    }

    /**
     * 新增菜单
     *
     * @param menuPo
     */
    @Override
    public int addMenu(MenuPo menuPo) {
        return systemDao.addMenu(menuPo);
    }

    /**
     * 验证是否有同一个父级菜单下有多个重名的菜单
     **/
    @Override
    public List<MenuPo> check() {
        return systemDao.check();
    }
}
