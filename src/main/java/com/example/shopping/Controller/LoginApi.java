package com.example.shopping.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import com.example.shopping.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@RestController
public class LoginApi {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        User user = userService.checkUser(username,password);
        if(user != null){
            JSONObject res = new JSONObject();
            res.put("username",username);
            user.setPassword(null);
            session.setAttribute("user",user);
            session.setAttribute("uid",user.getId());
            return new ResultBody<>(true,200,res);
        }else{
            return new ResultBody<>(false,501,"unknown user");
        }
    }
    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    public Object getPermissions(@RequestParam String username){
        return new ResultBody<>(true,200,userService.getPermissionsByUsername(username));
    }
}
