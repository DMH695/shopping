package com.example.shopping.dao;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll();
}