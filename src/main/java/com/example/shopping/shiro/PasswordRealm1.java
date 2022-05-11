package com.example.shopping.shiro;

import com.example.shopping.entity.Admin;
import com.example.shopping.entity.LoginType;
import com.example.shopping.entity.User;
import com.example.shopping.entity.UserToken;
import com.example.shopping.service.AdminService;
import com.example.shopping.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordRealm1 extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号
        UserToken userToken = (UserToken) authenticationToken;
        if (userToken.getLoginType() == LoginType.PASSWORD1) {
            //通过username从数据库中查找 User对象
            User user = userService.getUser(userToken.getUsername());
            if (user == null) {
                //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
                throw new UnknownAccountException("用户不存在");
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户名
                    user.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        } else {
            Admin admin = adminService.getAdminByName(userToken.getUsername());
            if (admin == null) {
                //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
                throw new UnknownAccountException("用户不存在");
            }
            //使用密码加盐的方式验证密码的安全，盐为用户注册时设置的用户名
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    admin, //用户名
                    admin.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(principalCollection.toString().contains("admin")){
            Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
            //添加角色和权限，SimpleAuthorizationInfo：授权信息
            for(String role : adminService.getRoleByUsername(admin.getUsername())){
                simpleAuthorizationInfo.addRole(role);
            }
            for(String permission : adminService.getPermissionsByUsername(admin.getUsername())){
                simpleAuthorizationInfo.addStringPermission(permission);
            }
        }else {
            User user = (User) principalCollection.getPrimaryPrincipal();
            //添加角色和权限，SimpleAuthorizationInfo：授权信息
            for(String role : userService.getRoleByUsername(user.getUsername())){
                simpleAuthorizationInfo.addRole(role);
            }
            for(String permission : userService.getPermissionsByUsername(user.getUsername())){
                simpleAuthorizationInfo.addStringPermission(permission);
            }
        }
        return simpleAuthorizationInfo;
    }
}
