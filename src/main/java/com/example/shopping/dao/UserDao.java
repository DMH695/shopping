package com.example.shopping.dao;

import com.example.shopping.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<User> {
    User getUserByUsernameAndPassword(String username,String password);
    void save(String username,String password);
    User findUser(int uid);
}
