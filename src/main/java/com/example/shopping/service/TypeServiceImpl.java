package com.example.shopping.service;

import com.example.shopping.dao.TypeDao;
import com.example.shopping.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    TypeDao typeDao;

    @Override
    public List<Type> getAllTypes() {
        return typeDao.findAll();
    }
}
