package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Role;
import com.hotel.core.service.RoleService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController("selectRoleController")
@RequestMapping("/select")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取角色信息
    @RequestMapping("/getRoleById")
    public JsonResult getRoleById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoleById");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Role role = roleService.selectRoleById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,role);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据用户名获取角色信息
    @RequestMapping("/getRoleByUsername")
    public JsonResult getRoleByUsername(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoleByUsername");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String username = ToolUtil.str("username",request);
            if (ToolUtil.equalBool(username) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"用户名不能为空");
                return jsonResult;
            }
            Set<String> roleList = roleService.selectRoleNameByUsername(username);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roleList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的角色列表
    @RequestMapping("/getRoleList")
    public JsonResult getRoleList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRoleList");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("roleStates",1);
            String msg = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Role> roleList = roleService.selectRoleListByLimit(map,KEYLIST);
            int counts = roleService.selectCounts(states,KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roleList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的角色列表
    @RequestMapping("/getRecycleRoleList")
    public JsonResult getRecycleRoleList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleRoleList");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("roleStates",2);
            String msg = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Role> roleList = roleService.selectRoleListByLimit(map,RECYCLEKEYLIST);
            int counts = roleService.selectCounts(states,RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roleList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索角色（模糊查询）
    @RequestMapping("/searchRole")
    public JsonResult searchRole(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchRole");
        try {
            String roleName = ToolUtil.str("roleName",request);
            String roleDescription = ToolUtil.str("roleDescription",request);
            if ((ToolUtil.equalBool(roleName)||ToolUtil.equalBool(roleDescription)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"条件不能为空");
                return jsonResult;
            }
            List<Role> roleList = roleService.searchRole(roleName, roleDescription);
            jsonResult = JsonResult.build(FLAG_SUCCESS,roleList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
