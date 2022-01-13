package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.CartsDao;
import com.example.shopping.entity.Carts;
import com.example.shopping.utils.ImageTools;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartsDao cartsDao;

    @Override
    public boolean insertCart(int uid, int gid, String name, String picture, int count, double price, String standard) {

        cartsDao.insertCart(uid, gid, name, picture, count, price, standard);
        return true;
    }

    @Override
    public double getPriceById(int id) {
        return cartsDao.getPriceById(id);
    }

    @Override
    public List<JSONObject> findId() {
        List<JSONObject> res = new ArrayList<>();
        for (Carts cart : cartsDao.findAll()) {
            JSONObject ids = new JSONObject();
            ids.put("uid", cart.getUid());
            ids.put("gid", cart.getGid());
            res.add(ids);
        }
        return res;
    }

    @Override
    public void update(int uid, int id, int count, String standard) {
        cartsDao.update(uid, id, count, standard);
    }

    @Override
    public String getNameById(int id) {
        return cartsDao.getNameById(id);
    }

    @Override
    public String getPictureById(int id) {
        return cartsDao.getPictureById(id);
    }

    @SneakyThrows
    @Override
    public List<JSONObject> findAll() {
        List<JSONObject> res = new ArrayList<>();
        for (Carts cart : cartsDao.findAll()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("gid",cart.getGid());
            jsonObject.put("name", cart.getName());
            jsonObject.put("picture", cart.getPicture());
            jsonObject.put("count", cart.getCount());
            jsonObject.put("price", cart.getPrice());
            jsonObject.put("standard", cart.getStandard());
            jsonObject.put("checked",cart.isChecked());
            res.add(jsonObject);
        }
        return res;
    }

    @Override
    public void updateCount(int uid, int gid, int count) {
        cartsDao.updateCount(uid, gid, count);
    }


    @Override
    public List<JSONObject> findCountAndPrice(int uid, List<Integer> gids) {
        List<JSONObject> res = new ArrayList<>();
        for (Integer gid : gids) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", cartsDao.findCountAndPrice(uid, gid).getCount());
            jsonObject.put("price", cartsDao.findCountAndPrice(uid, gid).getPrice());
            res.add(jsonObject);
        }
        return res;
    }

    @Override
    public void deleteCart(int uid, int gid) {
        cartsDao.deleteCart(uid, gid);
    }

    @SneakyThrows
    @Override
    public List<JSONObject> findOrder(int uid, List<Integer> gids) {
        List<JSONObject> res = new ArrayList<>();
        for (Integer gid : gids) {
            JSONObject jsonObject = new JSONObject();
            Carts carts = cartsDao.findOrder(uid, gid);
            jsonObject.put("name", carts.getName());
            jsonObject.put("picture", carts.getPicture());
            jsonObject.put("count", carts.getCount());
            jsonObject.put("price", carts.getPrice());
            jsonObject.put("standard", carts.getStandard());
            res.add(jsonObject);
        }
        return res;
    }
}