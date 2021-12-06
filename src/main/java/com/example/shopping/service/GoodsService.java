package com.example.shopping.service;

import com.example.shopping.entity.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getByTypename(String typename);
    Goods getById(int id);
}
