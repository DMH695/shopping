package com.example.shopping.entity;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken {
    private LoginType loginType;

    public UserToken(String username, String password, boolean rememberMe, LoginType loginType) {
        super(username, password, rememberMe);
        this.loginType = loginType;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
