package com.example.shopping.service;

import com.example.shopping.dao.GoodsDao;
import com.example.shopping.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    GoodsDao goodsDao;
    @Override
    public List<Goods> getByTypename(String typename) {
        return goodsDao.getByTypename(typename);
    }

    @Override
    public Goods getById(int id) {
        return goodsDao.getById(id);
    }
}
