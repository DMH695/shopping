package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.User;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;

public interface UserService {
    User checkUser(String username, String password);
    User save(String username,String password,String address,String telephone);
    JSONObject findUser(int uid);
}
