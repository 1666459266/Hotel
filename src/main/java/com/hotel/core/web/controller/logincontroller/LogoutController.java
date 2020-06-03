package com.hotel.core.web.controller.logincontroller;

import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class LogoutController extends BaseController {

    @RequestMapping("/logout")
    public JsonResult logout(){
        System.out.println("执行退出");
        JsonResult jsonResult = new JsonResult();
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //执行登出
        currentUser.logout();
        jsonResult = JsonResult.build(FLAG_SUCCESS,"退出成功");
        System.out.println("退出成功");
        return jsonResult;
    }

}
