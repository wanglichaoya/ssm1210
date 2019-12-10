/*
package com.controller.ecommerce;

import com.alibaba.fastjson.JSONArray;
import com.po.Product;
import com.service.EcommerceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

*/
/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/9/5
 * Time: 下午 05:36
 * Description:  购物车 控制器
 *//*


@RequestMapping("/ecommerce")
public class EcommerceController {
    @Resource
    private EcommerceService ecommerceService;

   */
/* @RequestMapping(value = "/queryShoppingCar", method = RequestMethod.GET)*//*

    @RequestMapping("/queryShoppingCar")
    @ResponseBody
    public JSONArray queryShoppingCar() {
       */
/* List<Product> productsList2 = new ArrayList<Product>();*//*

        List<Product> productsList = ecommerceService.queryShoppingCar();
        JSONArray jsonArray = null;
        if (productsList.size() > 0) {
            jsonArray = (JSONArray) JSONArray.toJSON(productsList);
        }

        return jsonArray;
    }
}
*/
