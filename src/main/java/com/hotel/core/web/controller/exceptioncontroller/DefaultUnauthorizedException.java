package com.hotel.core.web.controller.exceptioncontroller;

import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class DefaultUnauthorizedException extends BaseController {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public JsonResult defaultUnauthorizedException(HttpServletRequest request,HttpServletResponse response,Exception e) {
        JsonResult jsonResult = new JsonResult();
        jsonResult = JsonResult.build(FLAG_NO_PERMISSION,"对不起，你无权访问，请升级权限");
        return jsonResult;
    }

}
