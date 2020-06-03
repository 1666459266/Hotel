package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.RolePermission;
import com.hotel.core.service.RolePermissionService;
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

@RestController("selectRolePermissionController")
@RequestMapping("/select")
public class RolePermissionController extends BaseController {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取角色资源权限信息
    @RequestMapping("/getRolePermissionById")
    public JsonResult getRolePermissionById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRolePermissionById");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            RolePermission rolePermission = rolePermissionService.selectRolePermissionById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,rolePermission);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取角色资源权限列表
    @RequestMapping("/getRolePermissionList")
    public JsonResult getRolePermissionList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRolePermissionList");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String msg = "success";
            //执行查询并传入缓存的key
            List<RolePermission> rolePermissionList = rolePermissionService.selectRolePermissionList(KEYLIST);
            int counts = rolePermissionService.selectCounts(KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,rolePermissionList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
