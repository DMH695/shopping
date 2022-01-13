package com.example.shopping.entity;

import lombok.Data;

@Data
public class Carts {
    private int uid;
    private int gid;
    private String name;
    private String picture;
    private int count;
    private double price;
    private String standard;
    private boolean checked;
}
