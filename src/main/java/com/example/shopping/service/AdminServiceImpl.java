package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.AdminDao;
import com.example.shopping.entity.Admin;
import com.example.shopping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin getAdminByName(String username) {
        return adminDao.getAdminByName(username);
    }
    @Override
    public List<String> getPermissionsByUsername(String username) {
        String roles = adminDao.getRoles(username);
        List<String> roleList = StringToList(roles,"roles");
        List<String> permissions = new ArrayList<>();
        for(String role : roleList){
            for(String permission : StringToList(adminDao.getPermissionsByRoleName(role),"permissions")){
                permissions.add(permission);
            }
        }
        return permissions;
    }

    @Override
    public List<String> getRoleByUsername(String username) {
        String roles = adminDao.getRoles(username);
        return StringToList(roles,"roles");
    }



    public List<String> StringToList(String string,String type){
        JSONObject jsonObject = (JSONObject) JSONObject.parse(string);
        List<String> list = (List<String>) jsonObject.get(type);
        return list;
    }
}
