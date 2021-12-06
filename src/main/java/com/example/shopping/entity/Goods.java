package com.example.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
public class Goods {

    private int id;
    private String name;
    private String type;
    private String introduce;
    private double price;
    private String picture;
}
