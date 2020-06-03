package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.History;
import com.hotel.core.service.HistoryService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectHistoryController")
@RequestMapping("/select")
public class HistoryController extends BaseController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private RedisUtils redisUtils;
    //根据id获取信息
    @RequestMapping("/getHistoryById")
    public JsonResult getHistoryById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getHistoryById");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            History history = historyService.selectHistoryById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,history);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的信息
    @RequestMapping("/getHistoryList")
    public JsonResult getHistoryList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getHistoryList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
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
            List<History> historyList = historyService.selectHistoryListByLimit(map, KEYLIST);
            int counts = historyService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,historyList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的信息
    @RequestMapping("/getRecycleHistoryList")
    public JsonResult getRecycleHistoryList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleHistoryList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
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
            List<History> historyList = historyService.selectHistoryListByLimit(map, RECYCLEKEYLIST);
            int counts = historyService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,historyList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
