package com.example.shopping.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyAuthenticationFilter extends FormAuthenticationFilter {

    private static final String USR_LOGIN_URL = "/user/login";
    private static final String ADMIN_LOGIN_URL = "/admin/login";


    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        if(url.contains("/user/login")){
            WebUtils.issueRedirect(request, response, USR_LOGIN_URL);
        }
        else if(url.contains("/admin/login")){
            WebUtils.issueRedirect(request, response, ADMIN_LOGIN_URL);
        }
    }
}