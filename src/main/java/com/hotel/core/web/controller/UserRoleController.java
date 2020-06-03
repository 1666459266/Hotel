package com.hotel.core.web.controller;

import com.hotel.core.entity.UserRole;
import com.hotel.core.service.UserRoleService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RedisUtils redisUtils;

    //后台添加用户角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/insertUserRole")
    public JsonResult insertUserRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertUserRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String username = ToolUtil.str("username",request);
            Integer roleId = ToolUtil.integer("roleId",request);
            if ((ToolUtil.equalBool(username)&&ToolUtil.equalBool(roleId)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRole userRole = new UserRole();
            userRole.setUserName(username);
            userRole.setRoleId(roleId);
            //执行添加角色并清除用户角色列表的缓存
            int result = userRoleService.insertUserRole(userRole,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //后台修改用户角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/updateUserRole")
    public JsonResult updateUserRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateUserRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String username = ToolUtil.str("username",request);
            Integer roleId = ToolUtil.integer("roleId",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(username)&&ToolUtil.equalBool(roleId)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            UserRole userRole = userRoleService.selectUserRoleById(id);
            if (userRole == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户角色不存在");
                return jsonResult;
            }
            userRole.setUserName(username);
            userRole.setRoleId(roleId);
            //执行更新用户角色并清除用户角色列表的缓存
            int result = userRoleService.updateUserRole(userRole,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //后台删除用户角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteUserRole")
    public JsonResult deleteUserRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            UserRole userRole = userRoleService.selectUserRoleById(id);
            if (userRole == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户角色不存在");
                return jsonResult;
            }
            //执行删除用户角色并清除角色列表缓存
            int result = userRoleService.deleteUserRoleById(id, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //批量删除用户角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteUserRoleBatch")
    public JsonResult deleteUserRoleBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserRoleBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //执行删除并清除用户角色列表缓存
            int result = userRoleService.deleteBatch(ids,KEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("userRole::" + id);
            }
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
