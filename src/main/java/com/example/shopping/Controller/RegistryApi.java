package com.example.shopping.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;

import com.example.shopping.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class RegistryApi {
    @Autowired
    UserService userService;
    @ResponseBody
    @PostMapping("/registry/submit")
    public Object codeTest(@RequestBody JSONObject data, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //String code = data.getString("code");
        //if(session.getAttribute("RANDOMKEY").toString().equals(code)){
            String username = data.getString("username");
            String password = data.getString("password");
            String address = data.getString("address");
            String telephone = data.getString("telephone");
            //将用户保存到数据库
            password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            User user1 = userService.save(username,password,address,telephone);
            if(user1 != null){
                return new ResultBody<>(true,200,null);
            }
            else {
                return new ResultBody<>(false,501,"failed save");
            }
       //}else {
         //   return new ResultBody<>(false,502,"error validateCode");
        //}
    }
}

