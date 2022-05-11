package com.example.shopping.Controller;

import com.example.shopping.entity.LoginType;
import com.example.shopping.entity.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginApi {
    /**
     * 登录接口
     *
     * @param username
     * @param password
     */
    @PostMapping("/user/login")
    public String doLogin(String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                UserToken token = new UserToken(username, password, rememberMe, LoginType.PASSWORD1);
                subject.login(token);
                return "登录成功";
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "登录失败";
            }
        } else {
            return "已经登录成功";
        }
    }

    @PostMapping("/admin/login")
    public String admin(String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                UserToken token = new UserToken(username, password, rememberMe, LoginType.PASSWORD2);
                subject.login(token);
                return "登录成功";
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "登录失败";
            }
        } else {
            return "已经登录成功";
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public Object logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout success";
    }
}
