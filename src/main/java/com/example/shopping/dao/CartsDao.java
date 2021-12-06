package com.example.shopping.dao;

import com.example.shopping.entity.Carts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartsDao extends BaseDao<Carts>{
    void insertCart(int uid,int gid,String name,int count,double price);
    double getPriceById(int id);
    void update(int uid,int gid,int count);
    String getNameById(int id);
    String getPictureById(int id);
    void updateCount(int uid,int gid,int count);
    Carts findCountAndPrice(int uid,int gid);
    void deleteCart(int uid,int gid);
    Carts findOrder(int uid,int gid);
}
