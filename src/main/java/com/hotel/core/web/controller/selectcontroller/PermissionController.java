package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Permission;
import com.hotel.core.service.PermissionService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectPermissionController")
@RequestMapping("/select")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取资源权限信息
    @RequestMapping("/getPermissionById")
    public JsonResult getPermissionById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getPermissionById");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Permission permission = permissionService.selectPermissionById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,permission);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据角色名查询菜单列表
    @RequestMapping("/getPermissionByRoleName")
    public JsonResult getPermissionByRoleName(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getPermissionByRoleName");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String roleName = ToolUtil.str("roleName",request);
            if (ToolUtil.equalBool(roleName) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"角色名不能为空");
                return jsonResult;
            }
            List<Permission> parentPermission = permissionService.getPermissionByRoleName(roleName);
            jsonResult = JsonResult.build(FLAG_SUCCESS,parentPermission);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的资源权限列表
    @RequestMapping("/getPermissionList")
    public JsonResult getPermissionList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getPermissionList");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("permissionStates",1);
            String msg = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Permission> permissionList = permissionService.selectPermissionListBySortAscLimit(map, KEYLIST);
            int counts = permissionService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,permissionList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的资源权限列表
    @RequestMapping("/getRecyclePermissionList")
    public JsonResult getRecyclePermissionList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecyclePermissionList");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("permissionStates",2);
            String msg = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Permission> permissionList = permissionService.selectPermissionListBySortAscLimit(map, RECYCLEKEYLIST);
            int counts = permissionService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,permissionList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索权限（模糊查询）
    @RequestMapping("/searchPermission")
    public JsonResult searchPermission(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchPermission");
        try {
            String permissionName = ToolUtil.str("permissionName",request);
            String permissionDescribe = ToolUtil.str("permissionDescribe",request);
            if ((ToolUtil.equalBool(permissionName)||ToolUtil.equalBool(permissionDescribe)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"条件不能为空");
                return jsonResult;
            }
            List<Permission> permissionList = permissionService.searchPermission(permissionName, permissionDescribe);
            jsonResult = JsonResult.build(FLAG_SUCCESS,permissionList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据角色id查询资源权限
    @RequestMapping("/getPermissionListByRoleId")
    public JsonResult getPermissionListByRoleId(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getPermissionListByRoleId");
        try {
            Integer roleId = ToolUtil.integer("roleId", request);
            if (ToolUtil.equalBool(roleId) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"角色id不能为空");
                return jsonResult;
            }
            List<Permission> permissionList = permissionService.selectPermissionListByRoleId(roleId);
            jsonResult = JsonResult.build(FLAG_SUCCESS,permissionList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
