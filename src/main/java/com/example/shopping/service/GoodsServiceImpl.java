package com.example.shopping.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.example.shopping.dao.GoodsDao;
import com.example.shopping.entity.Goods;
import com.example.shopping.utils.ImageTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    GoodsDao goodsDao;
    @Override
    public List<JSONObject> getByTypename(String typename) throws IOException {
        List<JSONObject> res = new ArrayList<>();
        for(Goods goods : goodsDao.getByTypename(typename)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",goods.getId());
            jsonObject.put("name",goods.getName());
            jsonObject.put("introduce",goods.getIntroduce());
            jsonObject.put("price",goods.getPrice());
            //转化为base64编码
            /*BufferedImage bufferedImage = ImageIO.read(new File(goods.getPicture()));
            String imgBase64 = ImageTools.imgToBase64(bufferedImage);
            jsonObject.put("pciture",imgBase64);*/
            jsonObject.put("piceture",goods.getPicture());
            res.add(jsonObject);
        }
        return res;
    }

    @Override
    public JSONObject getById(int id) throws IOException {
        Goods goods = goodsDao.getById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",goods.getId());
        jsonObject.put("name",goods.getName());
        jsonObject.put("introduce",goods.getIntroduce());
        jsonObject.put("price",goods.getPrice());
        //转化为base64编码
        /*BufferedImage bufferedImage = ImageIO.read(new File(goods.getPicture()));
        String imgBase64 = ImageTools.imgToBase64(bufferedImage);
        jsonObject.put("pciture",imgBase64);*/
        jsonObject.put("picture",goods.getPicture());
        //将string转为array
        jsonObject.put("standard",JSON.parseArray(goods.getStandard()));
        return jsonObject;
    }

    @Override
    public Goods getGoodsByCondition(String word) {
        return goodsDao.getGoodsByCondition(word);
    }
}
