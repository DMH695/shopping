package com.example.shopping.service;

import com.example.shopping.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin getAdminByName(String username);
    List<String> getPermissionsByUsername(String username);
    List<String> getRoleByUsername(String username);
}
