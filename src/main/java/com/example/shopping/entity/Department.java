package com.example.shopping.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Department {
    private int id;
    private String name;
    private int fid;
    private List<Department> children = new ArrayList<>();
    private boolean travel = false;
}
