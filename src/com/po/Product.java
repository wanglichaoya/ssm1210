package com.po;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/9/5
 * Time: 下午 05:03
 * Description:  商品类
 */
public class Product {
    private int id;
    private String name;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
