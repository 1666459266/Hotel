package com.hotel.core.web.controller;

import com.hotel.core.entity.Role;
import com.hotel.core.service.RoleService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtils redisUtils;

    //后台添加角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/insertRole")
    public JsonResult insertRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String roleName = ToolUtil.str("roleName",request);
            String roleDescription = ToolUtil.str("roleDescription",request);
            if ((ToolUtil.equalBool(roleName)&&ToolUtil.equalBool(roleDescription)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Role role = new Role();
            role.setRoleName(roleName);
            role.setRoleDescription(roleDescription);
            role.setRoleStates(1);
            //执行添加角色并清除正常列表的缓存
            int result = roleService.insertRole(role,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"角色名已存在");
        }  catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //后台修改角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/updateRole")
    public JsonResult updateRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String roleName = ToolUtil.str("roleName",request);
            String roleDescription = ToolUtil.str("roleDescription",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(roleName)&&ToolUtil.equalBool(roleDescription)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Role role = roleService.selectRoleById(id);
            if (role == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该角色不存在");
                return jsonResult;
            }
            role.setRoleName(roleName);
            role.setRoleDescription(roleDescription);
            //执行更新角色并清除正常列表的缓存
            int result = roleService.updateRole(role,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"角色名已存在");
        }  catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //后台回收角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRole")
    public JsonResult recycleRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Role role = roleService.selectRoleById(id);
            if (role == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该角色不存在");
                return jsonResult;
            }
            role.setRoleStates(2);
            //执行回收角色并清除回收列表的缓存以及回收角色的缓存
            int result = roleService.updateRoleByStates(role,RECYCLEKEYLIST);
            //清除正常列表的缓存
            roleService.clearCache(KEYLIST);
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

    //将回收的角色恢复到正常状态
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleRole")
    public JsonResult recoveryRecycleRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Role role = roleService.selectRoleById(id);
            if (role == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该角色不存在");
                return jsonResult;
            }
            role.setRoleStates(1);
            //执行恢复角色并清除回收列表的缓存以及回收角色的缓存
            int result = roleService.updateRoleByStates(role,RECYCLEKEYLIST);
            //清除正常列表的缓存
            roleService.clearCache(KEYLIST);
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

    //后台删除角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRole")
    public JsonResult deleteRole(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRole");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Role role = roleService.selectRoleById(id);
            if (role == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该角色不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = roleService.deleteRoleById(id,RECYCLEKEYLIST);
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

    //批量回收角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRoleBatch")
    public JsonResult recycleRoleBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRoleBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //回收角色并清除回收列表缓存
            int result = roleService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("role::" + id);
            }
            //清除正常列表缓存
            roleService.clearCache(KEYLIST);
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

    //将回收的角色批量恢复到正常状态
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleRoleBatch")
    public JsonResult recoveryRecycleRoleBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleRoleBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //恢复角色并清除回收列表的缓存
            int result = roleService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("role::" + id);
            }
            //清除正常列表缓存
            roleService.clearCache(KEYLIST);
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

    //批量删除角色
    @RequiresRoles(value = {"root","admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteRoleBatch")
    public JsonResult deleteRoleBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteRoleBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //删除角色并清除回收列表的缓存
            int result = roleService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("role::" + id);
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
