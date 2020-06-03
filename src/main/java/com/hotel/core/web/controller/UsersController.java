package com.hotel.core.web.controller;

import com.hotel.core.entity.Users;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.*;
import com.hotel.core.utils.uploadfile.FileUploadTool;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //后台添加用户
    @RequestMapping("/insertUser")
    public JsonResult insertUsers(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                  HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertUser");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            String username = ToolUtil.str("username",request);
            String password = ToolUtil.str("password",request);
            String nickname = ToolUtil.str("nickname",request);
            String truename = ToolUtil.str("truename",request);
            String gender = ToolUtil.str("gender",request);
            String nation = ToolUtil.str("nation",request);
            String address = ToolUtil.str("address",request);
            Date birthday = ToolUtil.date2("birthday",request);
            String phone = ToolUtil.str("phone",request);
            String email = ToolUtil.str("email",request);
            String idcardNumber = ToolUtil.str("idcardNumber",request);
            if ((ToolUtil.equalBool(username)&&ToolUtil.equalBool(password)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Users users = new Users();
            users.setUsername(username);
            users.setPassword(Encryption.build(username,password).toString());
            users.setNickname(nickname);
            if (multipartFile != null) {
                //上传头像
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String avatar = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + avatar);
                if (avatar != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                users.setAvatar(avatar);
            }
            users.setTruename(truename);
            users.setGender(gender);
            users.setNation(nation);
            users.setAddress(address);
            users.setBirthday(birthday);
            users.setPhone(phone);
            users.setEmail(email);
            users.setIdcardNumber(idcardNumber);
            users.setMicroblogAccountStates(0);
            users.setQqAccountStates(0);
            users.setWechatAccountStates(0);
            users.setAlipayAccountStates(0);
            users.setBaiduAccountStates(0);
            users.setRegistrationDate(new Date());
            users.setMembershipScore("");
            users.setMembershipLevel(1);
            if (ToolUtil.equalBool(idcardNumber) == false){
                users.setRealnameAuthenticationStates(2);
            }else {
                users.setRealnameAuthenticationStates(1);
            }
            users.setStates(1);
            //执行添加用户并清除正常列表的缓存
            int result = usersService.insertUser(users,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"用户名已存在");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //后台修改用户
    @RequestMapping("/updateUser")
    public JsonResult updateUser(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                 HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateUser");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String username = ToolUtil.str("username",request);
            String password = ToolUtil.str("password",request);
            String nickname = ToolUtil.str("nickname",request);
            String truename = ToolUtil.str("truename",request);
            String gender = ToolUtil.str("gender",request);
            String nation = ToolUtil.str("nation",request);
            String address = ToolUtil.str("address",request);
            Date birthday = ToolUtil.date2("birthday",request);
            String phone = ToolUtil.str("phone",request);
            String email = ToolUtil.str("email",request);
            String idcardNumber = ToolUtil.str("idcardNumber",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(username)&&ToolUtil.equalBool(password)) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Users users = usersService.selectUsersById(id);
            if (users == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户不存在");
                return jsonResult;
            }

            users.setUsername(username);
            users.setPassword(Encryption.build(username,password).toString());
            users.setNickname(nickname);
            if (multipartFile != null) {
                //上传头像
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String avatar = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + avatar);
                if (avatar != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                users.setAvatar(avatar);
            }
            users.setTruename(truename);
            users.setGender(gender);
            users.setNation(nation);
            users.setAddress(address);
            users.setBirthday(birthday);
            users.setPhone(phone);
            users.setEmail(email);
            if (ToolUtil.equalBool(idcardNumber) == false){
                users.setRealnameAuthenticationStates(2);
            }else {
                users.setRealnameAuthenticationStates(1);
            }
            //执行更新用户并清除正常列表的缓存
            int result = usersService.updateUser(users,KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        }  catch (DataAccessException dae) {
            dae.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"用户名已存在");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //后台回收用户
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleUser")
    public JsonResult recycleUser(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleUser");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Users users = usersService.selectUsersById(id);
            if (users == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户不存在");
                return jsonResult;
            }
            users.setStates(2);
            //执行回收用户并清除回收列表的缓存
            int result = usersService.updateUserByStates(users,RECYCLEKEYLIST);
            //清除正常列表的缓存
            usersService.clearCache(KEYLIST);
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

    //将回收的用户恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleUser")
    public JsonResult recoveryRecycleUser(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleUser");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Users users = usersService.selectUsersById(id);
            if (users == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户不存在");
                return jsonResult;
            }
            users.setStates(1);
            //执行恢复用户并清除回收列表的缓存
            int result = usersService.updateUserByStates(users,RECYCLEKEYLIST);
            //清除正常列表的缓存
            usersService.clearCache(KEYLIST);
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

    //后台锁定用户
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/lockingUser")
    public JsonResult lockingUser(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...lockingUser");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Users users = usersService.selectUsersById(id);
            if (users == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户不存在");
                return jsonResult;
            }
            users.setStates(3);
            //执行锁定用户并清除锁定列表的缓存
            int result = usersService.updateUserByStates(users,LOCKINGKEYLIST);
            //清除正常列表的缓存
            usersService.clearCache(KEYLIST);
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

    //将锁定用户恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryLockingUser")
    public JsonResult recoveryLockingUser(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryLockingUser");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Users users = usersService.selectUsersById(id);
            if (users == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户不存在");
                return jsonResult;
            }
            users.setStates(1);
            //执行恢复用户并清除锁定列表的缓存
            int result = usersService.updateUserByStates(users,LOCKINGKEYLIST);
            //清除正常列表的缓存
            usersService.clearCache(KEYLIST);
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

    //后台删除用户
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteUser")
    public JsonResult deleteUser(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUser");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Users users = usersService.selectUsersById(id);
            if (users == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该用户不存在");
                return jsonResult;
            }
            //清除回收列表缓存以及该用户的所有角色权限信息
            int result = usersService.deleteUserById(id,users.getUsername(),RECYCLEKEYLIST);
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

    //批量回收用户
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleUserBatch")
    public JsonResult recycleUserBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleUserBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //回收用户并清除回收列表缓存
            int result = usersService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("users::" + id);
                Users users = usersService.selectUsersById((Integer) id);
                redisUtils.delete("username::" + users.getUsername());
            }
            //清除正常列表缓存
            usersService.clearCache(KEYLIST);
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

    //将回收的用户批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleUserBatch")
    public JsonResult recoveryRecycleUserBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleUserBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //恢复用户并清除回收列表缓存
            int result = usersService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("users::" + id);
                Users users = usersService.selectUsersById((Integer) id);
                redisUtils.delete("username::" + users.getUsername());
            }
            //清除正常列表缓存
            usersService.clearCache(KEYLIST);
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

    //批量删除用户
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteUserBatch")
    public JsonResult deleteUserBatch(@RequestBody(required = false) List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteUserBatch");
        redisUtils.setDataBase(0);
        System.out.println("Redis切换到[0]库");
        try {
            //删除用户并清除回收列表缓存
            int result = usersService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("users::" + id);
                Users users = usersService.selectUsersById((Integer) id);
                redisUtils.delete("username::" + users.getUsername());
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
