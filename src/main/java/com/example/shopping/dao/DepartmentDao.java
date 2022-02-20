package com.example.shopping.dao;

import com.example.shopping.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentDao extends BaseDao<Department>{
    @Override
    List<Department> findAll();
    List<Department> getById(int id);
    List<Department> findByFid(Integer id);
}
