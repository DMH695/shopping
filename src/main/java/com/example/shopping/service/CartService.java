package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface CartService {
    boolean insertCart(int uid,int gid,String name,String picture,int count,double price);
    double getPriceById(int id);
    List<JSONObject> findId();
    void update(int uid,int id,int count);
    String getNameById(int id);
    String getPictureById(int id);
    List<JSONObject> findAll();
    void updateCount(int uid,int gid,int count);
    List<JSONObject> findCountAndPrice(int uid,List<Integer> gids);
    void deleteCart(int uid,int gid);
    List<JSONObject> findOrder(int uid,List<Integer> gids);
}
