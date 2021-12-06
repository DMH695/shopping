package com.example.shopping.Controller;

import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;

import com.example.shopping.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegistryApi {
    @Autowired
    UserService userService;
    @ResponseBody
    @PostMapping("registry/submit")
    public Object codeTest(@RequestParam String username,@RequestParam String password, @RequestParam String code, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("RANDOMKEY").toString().equals(code)){
            //将用户保存到数据库
            User user1 = userService.save(username,password);
            if(user1 != null){
                return new ResultBody<>(true,200,null);
            }
            else {
                return new ResultBody<>(false,501,"failed save");
            }
        }else {
            return new ResultBody<>(false,502,"error validateCode");
        }
    }


    @ResponseBody
    @PostMapping("/submit")
    public Object saveUser(@RequestParam String username,@RequestParam String password){
        //将用户保存到数据库
        User user1 = userService.save(username, password);
        if(user1 != null){
            return new ResultBody<>(true,200,null);
        }
        else {
            return new ResultBody<>(false,401,"failed save");
        }
    }
}

