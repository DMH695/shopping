package com.example.shopping.entity;

import lombok.Data;

@Data
public class Admin {
    private int id;
    private String username;
    private String password;
    private String role;
    private String permissions;
}
