package com.example.shopping.dao;

import com.example.shopping.entity.Goods;
import com.example.shopping.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDao extends BaseDao<Type>{
    @Override
    List<Type> findAll();
}
