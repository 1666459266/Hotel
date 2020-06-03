package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Fitness;
import com.hotel.core.service.FitnessService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectFitnessController")
@RequestMapping("/select")
public class FitnessController extends BaseController {

    @Autowired
    private FitnessService fitnessService;
    @Autowired
    private RedisUtils redisUtils;
    //根据id获取器材信息
    @RequestMapping("/getFitnessById")
    public JsonResult getFitnessById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getFitnessById");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Fitness fitness = fitnessService.selectFitnessById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,fitness);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的器材信息
    @RequestMapping("/getFitnessList")
    public JsonResult getFitnessList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getFitnessList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            Integer equipmentStates = ToolUtil.integer("equipmentStates",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("equipmentStates",equipmentStates);
            map.put("existStates",1);
            String msg = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Fitness> fitnessList = fitnessService.selectFitnessListByLimit(map, KEYLIST);
            int counts = fitnessService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,fitnessList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的器材信息
    @RequestMapping("/getRecycleFitnessList")
    public JsonResult getRecycleFitnessList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleFitnessList");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            Integer equipmentStates = ToolUtil.integer("equipmentStates",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("equipmentStates",equipmentStates);
            map.put("existStates",2);
            String msg = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Fitness> fitnessList = fitnessService.selectFitnessListByLimit(map, RECYCLEKEYLIST);
            int counts = fitnessService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,fitnessList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
