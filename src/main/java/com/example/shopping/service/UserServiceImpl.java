package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username,password);
    }

    @Override
    public User save(String username, String password) {
        userDao.save(username,password);
        return new User(username,password);
    }

    @Override
    public JSONObject findUser(int uid) {
        User user = userDao.findUser(uid);
        JSONObject res = new JSONObject();
        res.put("username",user.getUsername());
        res.put("address",user.getAddress());
        res.put("telephone",user.getTelephone());
        return res;
    }
}
