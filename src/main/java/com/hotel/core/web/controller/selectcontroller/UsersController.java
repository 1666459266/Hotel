package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Users;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectUsersController")
@RequestMapping("/select")
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取用户信息
    @RequestMapping("/getUserById")
    public JsonResult getUserById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserById");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Users users = usersService.selectUsersById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,users);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据用户名获取用户信息
    @RequestMapping("/getUserByUsername")
    public JsonResult getUserByUsername(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserByUsername");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String username = ToolUtil.str("username",request);
            if (ToolUtil.equalBool(username) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"用户名不能为空");
                return jsonResult;
            }
            Users users = usersService.selectUserByUsername(username);
            jsonResult = JsonResult.build(FLAG_SUCCESS,users);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的用户列表
    @RequestMapping("/getUserList")
    public JsonResult getUserList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserList");
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
            map.put("states",1);
            String msg  = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Users> usersList = usersService.selectUserListByLimit(map,KEYLIST);
            int counts = usersService.selectCounts(states,KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的用户列表
    @RequestMapping("/getRecycleUserList")
    public JsonResult getRecycleUserList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleUserList");
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
            map.put("states",2);
            String msg  = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Users> usersList = usersService.selectUserListByLimit(map,RECYCLEKEYLIST);
            int counts = usersService.selectCounts(states,RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取锁定的用户列表
    @RequestMapping("/getLockingUserList")
    public JsonResult getLockingUserList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getLockingUserList");
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
            map.put("states",3);
            String msg  = "success";
            int states = 3;
            //执行查询并传入缓存的key
            List<Users> usersList = usersService.selectUserListByLimit(map,LOCKINGKEYLIST);
            int counts = usersService.selectCounts(states,LOCKINGKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索用户（模糊查询）
    @RequestMapping("/searchUser")
    public JsonResult searchUser(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchUser");
        try {
            String username = ToolUtil.str("username",request);
            String phone = ToolUtil.str("phone",request);
            String email = ToolUtil.str("email",request);
            if ((ToolUtil.equalBool(username)||ToolUtil.equalBool(phone)||ToolUtil.equalBool(email)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"条件不能为空");
                return jsonResult;
            }
            List<Users> usersList = usersService.searchUser(username,phone,email);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据会员等级降序排序
    @RequestMapping("/getUserListByMembershipLevelSortDesc")
    public JsonResult getUserListByMembershipLevelSortDesc(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserListByMembershipLevelSortDesc");
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
            map.put("states",1);
            List<Users> usersList = usersService.selectUserListByMembershipLevelSortDesc(map,KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据会员等级升序排序
    @RequestMapping("/getUserListByMembershipLevelSortAsc")
    public JsonResult getUserListByMembershipLevelSortAsc(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserListByMembershipLevelSortAsc");
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
            map.put("states",1);
            List<Users> usersList = usersService.selectUserListByMembershipLevelSortAsc(map,KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据性别筛选
    @RequestMapping("/getUserListByGender")
    public JsonResult getUserListByGender(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserListByGender");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            String gender = ToolUtil.str("gender",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)&&ToolUtil.equalBool(gender)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("gender",gender);
            map.put("states",1);
            List<Users> usersList = usersService.selectUserListByGender(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据角色筛选
    @RequestMapping("/getUserListByRole")
    public JsonResult getUserListByRole(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getUserListByRole");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            Integer roleId = ToolUtil.integer("roleId",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)&&ToolUtil.equalBool(roleId)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("roleId",roleId);
            map.put("states",1);
            List<Users> usersList = usersService.selectUserListByRole(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,usersList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
