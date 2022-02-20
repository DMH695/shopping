package com.example.shopping.service;

import com.example.shopping.dao.DepartmentDao;
import com.example.shopping.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public List<Department> findAllTwoFloor(){
        List<Department> res = new ArrayList<>();
        for(Department department : departmentDao.findAll()){
            if(department.getFid() == 0){
                res.add(department);
            }
        }
        for(Department department : res){
            int id = department.getId();
            List<Department> children = new ArrayList<>();
            for(Department department1 : departmentDao.findAll()){
                if(department1.getFid() == id){
                    children.add(department1);
                }
            }
            department.setChildren(children);
        }
        return res;
    }

    @Override
    public List<Department> findAllMoreFloor() {
        List<Department> res = getTree(0);
        return res;
    }

    private List<Department> getTree(Integer fid) {
        List<Department> res = new ArrayList<>();
        List<Department> list = departmentDao.findByFid(fid);
        for(Department department : list){
            List news = getTree(department.getId());
            if(news == null || news.size() == 0){
                res.add(department);
                continue;
            }else {
                department.setChildren(news);
                res.add(department);
            }
        }
        return res;
    }
}
