package com.example.shopping.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//未部署
class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在请求域中获取session进而获取user
        if(request.getSession().getAttribute("user") == null){
            return false;//表示不往下执行
        }
        return true;
    }
}
