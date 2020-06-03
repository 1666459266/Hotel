package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.UserRole;
import com.hotel.core.service.UserRoleService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController("selectUserRoleController")
@RequestMapping("/select")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取用户角色信息
    @RequestMapping("/getUserRoleById")
    public JsonResult getUserRoleById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoleById");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            UserRole userRole = userRoleService.selectUserRoleById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRole);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取用户角色列表
    @RequestMapping("/getUserRoleList")
    public JsonResult getUserRoleList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserRoleList");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String msg = "success";
            //执行查询并存入缓存的key
            List<UserRole> userRoleList = userRoleService.selectUserRoleList(KEYLIST);
            int result = userRoleService.selectCounts(KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,userRoleList,msg,result);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
