package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.Goods;

import java.io.IOException;
import java.util.List;

public interface GoodsService {
    List<JSONObject> getByTypename(String typename) throws IOException;
    JSONObject getById(int id) throws IOException;
    Goods getGoodsByCondition(String word);
}
