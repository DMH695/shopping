package com.example.shopping.Controller;


import com.example.shopping.utils.ValidateCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateApi {
    // 生成验证码图片
    @RequestMapping("/registry")
    @ResponseBody
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {

        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ValidateCode validateCode = new ValidateCode();

            // 直接返回图片
            validateCode.getRandomCodeImage(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    //局部刷新验证码
    @RequestMapping("/registry/getImage")
    @ResponseBody
    public void getImage(HttpServletRequest request, HttpServletResponse response){
        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ValidateCode validateCode = new ValidateCode();

            // 直接返回图片
            validateCode.getRandomCodeImage(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
