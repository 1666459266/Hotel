package com.hotel.core.web.controller.logincontroller;

import com.hotel.core.config.CustomToken;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class LoginController extends BaseController {

    @RequestMapping("/login")
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        System.out.println("执行登录");
        JsonResult jsonResult = new JsonResult();
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //判断当前用户是否认证
        if (!currentUser.isAuthenticated()){
            CustomToken usernamePasswordToken = new CustomToken(username,password);
            try {
                currentUser.login(usernamePasswordToken);
            } catch (UnknownAccountException uae) {
                jsonResult = JsonResult.build(FLAG_FAILED,"账户不存在");
                return jsonResult;
            } catch (IncorrectCredentialsException ice) {
                jsonResult = JsonResult.build(FLAG_FAILED,"密码错误");
                return jsonResult;
            } catch (LockedAccountException lae) {
                jsonResult = JsonResult.build(FLAG_FAILED,"账户被锁定");
                return jsonResult;
            } catch (AuthenticationException ae) {

            }
        }
        jsonResult = JsonResult.build(FLAG_SUCCESS,"登录成功",username);
        System.out.println("登录成功");
        return jsonResult;
    }

}
