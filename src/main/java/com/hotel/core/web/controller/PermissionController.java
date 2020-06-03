package com.hotel.core.web.controller;

import com.hotel.core.entity.Permission;
import com.hotel.core.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisUtils redisUtils;

    //后台添加资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/insertPermission")
    public JsonResult insertPermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertPermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String permissionName = ToolUtil.str("permissionName",request);
            String permissionDescribe = ToolUtil.str("permissionDescribe",request);
            String permissionKey = ToolUtil.str("permissionKey",request);
            String permissionValue = ToolUtil.str("permissionValue",request);
            Integer parentSort = ToolUtil.integer("parentSort",request);
            if ((ToolUtil.equalBool(permissionKey)&&ToolUtil.equalBool(permissionValue)&&ToolUtil.equalBool(parentSort)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Permission permission = new Permission();
            permission.setPermissionName(permissionName);
            permission.setPermissionDescribe(permissionDescribe);
            permission.setPermissionKey(permissionKey);
            permission.setPermissionValue(permissionValue);
            permission.setParentSort(parentSort);
            permission.setPermissionStates(1);
            //执行添加资源权限并清除正常列表的缓存
            int result = permissionService.insertPermission(permission, KEYLIST);
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

    //后台修改资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/updatePermission")
    public JsonResult updatePermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updatePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String permissionName = ToolUtil.str("permissionName",request);
            String permissionDescribe = ToolUtil.str("permissionDescribe",request);
            String permissionKey = ToolUtil.str("permissionKey",request);
            String permissionValue = ToolUtil.str("permissionValue",request);
            Integer parentPermissionId = ToolUtil.integer("parentPermissionId",request);
            Integer parentSort = ToolUtil.integer("parentSort",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(permissionKey)&&ToolUtil.equalBool(permissionValue)&&ToolUtil.equalBool(parentPermissionId)&&ToolUtil.equalBool(parentSort)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Permission permission = permissionService.selectPermissionById(id);
            if (permission == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该资源权限不存在");
                return jsonResult;
            }
            permission.setPermissionName(permissionName);
            permission.setPermissionDescribe(permissionDescribe);
            permission.setPermissionKey(permissionKey);
            permission.setPermissionValue(permissionValue);
            permission.setParentPermissionId(parentPermissionId);
            permission.setParentSort(parentSort);
            //执行修改资源权限并清除正常列表的缓存
            int result = permissionService.updatePermission(permission, KEYLIST);
            if (result > 0){
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

    //后台回收资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recyclePermission")
    public JsonResult recyclePermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recyclePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Permission permission = permissionService.selectPermissionById(id);
            if (permission == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该资源权限不存在");
                return jsonResult;
            }
            permission.setPermissionStates(2);
            //执行回收资源权限并清除回收列表的缓存
            int result = permissionService.updatePermissionByStates(permission, RECYCLEKEYLIST);
            //清除正常列表的缓存
            permissionService.clearCache(KEYLIST);
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

    //将资源权限状态恢复到正常状态
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecyclePermission")
    public JsonResult recoveryRecyclePermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecyclePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Permission permission = permissionService.selectPermissionById(id);
            if (permission == null){
                jsonResult = JsonResult.build(FLAG_FAILED,"该资源权限不存在");
                return jsonResult;
            }
            permission.setPermissionStates(1);
            //执行恢复资源权限并清除回收列表的缓存
            int result = permissionService.updatePermissionByStates(permission, RECYCLEKEYLIST);
            //清除正常列表的缓存
            permissionService.clearCache(KEYLIST);
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

    //后台删除资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deletePermission")
    public JsonResult deletePermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deletePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Permission permission = permissionService.selectPermissionById(id);
            if (permission == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该资源权限不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = permissionService.deletePermissionById(id,RECYCLEKEYLIST);
            if (result > 0){
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

    //批量回收资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recyclePermissionBatch")
    public JsonResult recyclePermissionBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deletePermission");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //回收资源权限并清除回收列表缓存
            int result = permissionService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("permission::" + id);
            }
            //清除正常列表的缓存
            permissionService.clearCache(KEYLIST);
            if (result > 0){
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

    //将回收的资源权限批量恢复到正常状态
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecyclePermissionBatch")
    public JsonResult recoveryRecyclePermissionBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecyclePermissionBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //恢复资源权限并清除回收列表缓存
            int result = permissionService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("permission::" + id);
            }
            //清除正常列表的缓存
            permissionService.clearCache(KEYLIST);
            if (result > 0){
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

    //批量删除资源权限
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deletePermissionBatch")
    public JsonResult deletePermissionBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deletePermissionBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //删除资源权限并清除回收列表缓存
            int result = permissionService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("permission::" + id);
            }
            if (result > 0){
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
