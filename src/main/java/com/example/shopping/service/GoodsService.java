package com.example.shopping.service;

import com.example.shopping.entity.Goods;

import java.io.IOException;
import java.util.List;

public interface GoodsService {
    List<Goods> getByTypename(String typename) throws IOException;
    Goods getById(int id) throws IOException;
}
