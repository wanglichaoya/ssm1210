package com.dao;

import com.po.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/9/5
 * Time: 下午 05:44
 * Description:
 */
public interface EcommerceDao {
    List<Product> queryShoppingCar();
}
