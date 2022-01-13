package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username,password);
    }

    @Override
    public User save(String username, String password, String address, String telephone) {
        User user = new User(username,password,address,telephone);
        userDao.save(username, password,address,telephone);
        return user;
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

    @Override
    public List<String> getPermissionsByUsername(String username) {
        String roles = userDao.getRoles(username);
        List<String> roleList = StringToList(roles,"roles");
        List<String> permissions = new ArrayList<>();
        for(String role : roleList){
            for(String permission : StringToList(userDao.getPermissionsByRoleName(role),"permissions")){
                permissions.add(permission);
            }
        }
        return permissions;
    }

    @Override
    public List<String> getRoleByUsername(String username) {
        String roles = userDao.getRoles(username);
        return StringToList(roles,"roles");
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }


    public List<String> StringToList(String string,String type){
        JSONObject jsonObject = (JSONObject) JSONObject.parse(string);
        List<String> list = (List<String>) jsonObject.get(type);
        return list;
    }
}

