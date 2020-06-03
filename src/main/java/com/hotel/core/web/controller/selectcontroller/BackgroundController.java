package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Background;
import com.hotel.core.service.BackgroundService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectBackgroundController")
@RequestMapping("/select")
public class BackgroundController extends BaseController {

    @Autowired
    private BackgroundService backgroundService;
    @Autowired
    private RedisUtils redisUtils;
    //根据id查询图片
    @RequestMapping("/getBackgroundById")
    public JsonResult getBackgroundById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getBackgroundById");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Background background = backgroundService.selectBackgroundById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,background);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的图片列表
    @RequestMapping("/getBackgroundList")
    public JsonResult getBackgroundList(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getBackgroundList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
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
            map.put("existStates",1);
            String msg = "success";
            int states = 1;
            List<Background> backgroundList = backgroundService.selectBackgroundListByLimit(map,KEYLIST);
            int counts = backgroundService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,backgroundList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }
    //获取回收状态的菜单列表
    @RequestMapping("/getRecycleBackgroundList")
    public JsonResult getRecycleBackgroundList(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleBackgroundList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
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
            map.put("existStates",2);
            String msg = "success";
            int states = 2;
            List<Background> backgroundList = backgroundService.selectBackgroundListByLimit(map,RECYCLEKEYLIST);
            int counts = backgroundService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,backgroundList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
