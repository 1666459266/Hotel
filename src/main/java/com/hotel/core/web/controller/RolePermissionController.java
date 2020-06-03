package com.hotel.core.web.controller;

import com.hotel.core.entity.RolePermission;
import com.hotel.core.service.RolePermissionService;
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
@RequestMapping("/rolePermission")
public class RolePermissionController extends BaseController {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RedisUtils redisUtils;

    //添加角色资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/insertRolePermission")
    public JsonResult insertRolePermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertRolePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer roleId = ToolUtil.integer("roleId",request);
            Integer permissionId = ToolUtil.integer("permissionId",request);
            if ((ToolUtil.equalBool(roleId)&&ToolUtil.equalBool(permissionId)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            //执行添加角色资源权限并清除列表的缓存
            int result = rolePermissionService.insertRolePermission(rolePermission, KEYLIST);
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

    //修改角色资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/updateRolePermission")
    public JsonResult updateRolePermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateRolePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            Integer roleId = ToolUtil.integer("roleId",request);
            Integer permissionId = ToolUtil.integer("permissionId",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(roleId)&&ToolUtil.equalBool(permissionId)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            RolePermission rolePermission = rolePermissionService.selectRolePermissionById(id);
            if (rolePermission == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该角色资源权限不存在");
                return jsonResult;
            }
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            //执行修改角色资源权限并清除列表的缓存
            int result = rolePermissionService.updateRolePermission(rolePermission, KEYLIST);
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

    //删除角色资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRolePermission")
    public JsonResult deleteRolePermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRolePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            RolePermission rolePermission = rolePermissionService.selectRolePermissionById(id);
            if (rolePermission == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该角色资源权限不存在");
                return jsonResult;
            }
            //执行删除角色资源权限并清除角色资源权限列表缓存
            int result = rolePermissionService.deleteRolePermissionById(id, KEYLIST);
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

    //批量删除角色资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRolePermissionBatch")
    public JsonResult deleteRolePermissionBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRolePermissionBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //删除角色资源权限并清除角色资源权限列表缓存
            int result = rolePermissionService.deleteBatch(ids,KEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("rolePermission::" + id);
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
