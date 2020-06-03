package com.hotel.core.web.controller.logincontroller;

import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController extends BaseController {

    @RequestMapping("/login")
    public JsonResult login(){
        JsonResult jsonResult = new JsonResult();
        jsonResult = JsonResult.build(FLAG_FAILED,"请登录");
        return jsonResult;
    }

    @RequestMapping("/success")
    public JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult = JsonResult.build(FLAG_SUCCESS,"登录成功");
        return jsonResult;
    }

    @RequestMapping("/unauthorized")
    public JsonResult unauthorized(){
        JsonResult jsonResult = new JsonResult();
        jsonResult = JsonResult.build(FLAG_NO_PERMISSION,"无权限");
        return jsonResult;
    }

}
