package com.example.shopping.service;

import com.example.shopping.dao.GoodsDao;
import com.example.shopping.entity.Goods;
import com.example.shopping.utils.ImageTools;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    GoodsDao goodsDao;
    @Override
    public List<Goods> getByTypename(String typename) throws IOException {
        List<Goods> res =goodsDao.getByTypename(typename);
        for(Goods goods : res){
            //转化为base64编码
            BufferedImage bufferedImage = ImageIO.read(new File(goods.getPicture()));
            String imgBase64 = ImageTools.imgToBase64(bufferedImage);
            goods.setPicture(imgBase64);
        }
        return res;
    }

    @Override
    public Goods getById(int id) throws IOException {
        Goods res = goodsDao.getById(id);
        BufferedImage bufferedImage = ImageIO.read(new File(res.getPicture()));
        String imgBase64 = ImageTools.imgToBase64(bufferedImage);
        res.setPicture(imgBase64);
        return res;
    }
}
