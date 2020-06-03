package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Message;
import com.hotel.core.service.MessageService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectMessageController")
@RequestMapping("/select")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private RedisUtils redisUtils;
    //根据id获取信息
    @RequestMapping("/getMessageById")
    public JsonResult getMessageById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getMessageById");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Message message = messageService.selectMessageById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,message);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的信息
    @RequestMapping("/getMessageList")
    public JsonResult getMessageList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getMessageList");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
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
            map.put("existStates",1);
            String msg = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Message> messageList = messageService.selectMessageListByLimit(map, KEYLIST);
            int counts = messageService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,messageList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的信息
    @RequestMapping("/getRecycleMessageList")
    public JsonResult getRecycleMessageList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleMessageList");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
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
            map.put("existStates",2);
            String msg = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Message> messageList = messageService.selectMessageListByLimit(map, RECYCLEKEYLIST);
            int counts = messageService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,messageList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
