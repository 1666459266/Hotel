package com.hotel.core.web.controller;

import com.hotel.core.entity.Message;
import com.hotel.core.entity.Users;
import com.hotel.core.service.MessageService;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertMessage")
    public JsonResult insertMessage(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertMessage");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            String theme = ToolUtil.str("theme",request);
            String content = ToolUtil.str("content",request);
            if ((ToolUtil.equalBool(theme)&&ToolUtil.equalBool(content) == false)){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Message message = new Message();
            message.setTheme(theme);
            message.setContent(content);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            message.setUserId(users.getId());
            message.setUsername(users.getUsername());
            message.setTime(new Date());
            message.setExistStates(1);
            //执行添加并清除正常列表的缓存
            int result = messageService.insertMessage(message, KEYLIST);
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

    //修改信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateMessage")
    public JsonResult updateMessage(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateMessage");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String theme = ToolUtil.str("theme",request);
            String content = ToolUtil.str("content",request);
            if(ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Message message = messageService.selectMessageById(id);
            if (message == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该信息不存在");
                return jsonResult;
            }
            message.setTheme(theme);
            message.setContent(content);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            message.setUserId(users.getId());
            message.setUsername(users.getUsername());
            message.setTime(new Date());
            //执行修改并清除正常列表的缓存
            int result = messageService.updateMessage(message, KEYLIST);
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

    //回收信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleMessage")
    public JsonResult recycleMessage(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleMessage");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if(ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Message message = messageService.selectMessageById(id);
            if (message == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该信息不存在");
                return jsonResult;
            }
            message.setExistStates(2);
            //清除正常的列表缓存
            messageService.updateMessageByStates(message,KEYLIST);
            //执行回收并清除正常列表的缓存
            int result = messageService.updateMessageByStates(message, RECYCLEKEYLIST);
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

    //回收信息恢复
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRecycleMessage")
    public JsonResult recycleRecycleMessage(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRecycleMessage");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if(ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Message message = messageService.selectMessageById(id);
            if (message == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该信息不存在");
                return jsonResult;
            }
            message.setExistStates(1);
            //清除正常的列表缓存
            messageService.updateMessageByStates(message,KEYLIST);
            //执行回收并清除正常列表的缓存
            int result = messageService.updateMessageByStates(message, RECYCLEKEYLIST);
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

    //删除信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteMessage")
    public JsonResult deleteMessage(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteMessage");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if(ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Message message = messageService.selectMessageById(id);
            if (message == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该信息不存在");
                return jsonResult;
            }
            //执行删除并清除正常列表的缓存
            int result = messageService.deleteMessageById(id, RECYCLEKEYLIST);
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

    //批量回收信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleMessageBatch")
    public JsonResult recycleMessageBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleMessageBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //回收信息并清除回收列表缓存
            int result = messageService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("message:" + id);
            }
            //清除正常列表的缓存
            messageService.clearCache(KEYLIST);
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

    //将回收的信息批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleMessageBatch")
    public JsonResult recoveryRecycleMessageBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleMessageBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //恢复信息并清除回收列表缓存
            int result = messageService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("message:" + id);
            }
            //清除正常列表的缓存
            messageService.clearCache(KEYLIST);
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

    //批量删除信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteMessageBatch")
    public JsonResult deleteMessageBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteMessageBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //删除信息并清除回收列表缓存
            int result = messageService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("message:" + id);
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
