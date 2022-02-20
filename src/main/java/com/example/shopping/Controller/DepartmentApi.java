package com.example.shopping.Controller;

import com.example.shopping.service.DepartmentService;
import com.example.shopping.utils.ResultBody;
import com.sun.org.apache.xpath.internal.operations.Gt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentApi {
    @Autowired
    DepartmentService departmentService;

    /*@RequestMapping(value = "",method = RequestMethod.GET)
    public Object findAll(){
        return new ResultBody<>(true,200,departmentService.findAllTwoFloor());
    }*/
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Object findAll(){
        return new ResultBody<>(true,200,departmentService.findAllMoreFloor());
    }
}
