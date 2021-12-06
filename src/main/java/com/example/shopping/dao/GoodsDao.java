package com.example.shopping.dao;

import com.example.shopping.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsDao extends BaseDao<Goods>{
    List<Goods> getByTypename(String typename);
    Goods getById(int id);
}
