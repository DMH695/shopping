package com.example.shopping.dao;

import com.example.shopping.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
    Admin getAdminByName(String username);
    String getRoles(String username);
    String getPermissionsByRoleName(String roleName);
}
