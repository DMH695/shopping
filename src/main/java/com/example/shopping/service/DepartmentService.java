package com.example.shopping.service;

import com.example.shopping.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllTwoFloor();
    List<Department> findAllMoreFloor();
}
