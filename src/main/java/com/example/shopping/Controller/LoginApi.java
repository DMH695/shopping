package com.example.shopping.Controller;

import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import com.example.shopping.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
@RestController
public class LoginApi {
    @Autowired
    UserService userService;
    @RequestMapping(method = RequestMethod.POST)
    public Object login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            session.setAttribute("uid",user.getId());
            return new ResultBody<>(true,200,null);
        }else{
            return new ResultBody<>(false,501,"unknown user");
        }
    }
}
