package com.hotel.core.web.controller;

import com.hotel.core.entity.Opinion;
import com.hotel.core.entity.Users;
import com.hotel.core.service.OpinionService;
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

@RestController
@RequestMapping("opinion")
public class OpinionController extends BaseController {

    @Autowired
    private OpinionService opinionService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加意见
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertOpinion")
    public JsonResult insertOpinion(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertOpinion");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            String problemType = ToolUtil.str("problemType",request);
            String problemDescription = ToolUtil.str("problemDescription",request);
            if ((ToolUtil.equalBool(problemType)&&ToolUtil.equalBool(problemDescription) == false)){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Opinion opinion = new Opinion();
            opinion.setProblemType(problemType);
            opinion.setProblemDescription(problemDescription);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            opinion.setUserId(users.getId());
            opinion.setUsername(users.getUsername());
            opinion.setFeedbackTime(new Date());
            opinion.setDisposeStates(1);
            //执行添加并清除正常列表的缓存
            int result = opinionService.insertOpinion(opinion, KEYLIST);
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

    //修改意见
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateOpinion")
    public JsonResult updateOpinion(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateOpinion");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String problemType = ToolUtil.str("problemType",request);
            String problemDescription = ToolUtil.str("problemDescription",request);
            if(ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(problemType)&&ToolUtil.equalBool(problemDescription) == false)){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Opinion opinion = opinionService.selectOpinionById(id);
            if (opinion == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该意见不存在");
                return jsonResult;
            }
            opinion.setProblemType(problemType);
            opinion.setProblemDescription(problemDescription);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            opinion.setUserId(users.getId());
            opinion.setUsername(users.getUsername());
            opinion.setFeedbackTime(new Date());
            //执行修改并清除正常列表的缓存
            int result = opinionService.updateOpinion(opinion, KEYLIST);
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

    //回收意见
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleOpinion")
    public JsonResult recycleOpinion(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleOpinion");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if(ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Opinion opinion = opinionService.selectOpinionById(id);
            if (opinion == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该意见不存在");
                return jsonResult;
            }
            opinion.setDisposeStates(2);
            //清除正常的列表缓存
            opinionService.updateOpinionByStates(opinion,KEYLIST);
            //执行回收并清除正常列表的缓存
            int result = opinionService.updateOpinionByStates(opinion, RECYCLEKEYLIST);
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

    //回收意见恢复
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRecycleOpinion")
    public JsonResult recycleRecycleOpinion(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRecycleOpinion");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if(ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Opinion opinion = opinionService.selectOpinionById(id);
            if (opinion == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该意见不存在");
                return jsonResult;
            }
            opinion.setDisposeStates(1);
            //清除正常的列表缓存
            opinionService.updateOpinionByStates(opinion,KEYLIST);
            //执行回收并清除正常列表的缓存
            int result = opinionService.updateOpinionByStates(opinion, RECYCLEKEYLIST);
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

    //删除意见
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteOpinion")
    public JsonResult deleteOpinion(HttpServletRequest request, HttpServletResponse response){
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
            Opinion opinion = opinionService.selectOpinionById(id);
            if (opinion == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该意见不存在");
                return jsonResult;
            }
            //执行删除并清除正常列表的缓存
            int result = opinionService.deleteOpinionById(id, RECYCLEKEYLIST);
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

    //批量回收意见
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleOpinionBatch")
    public JsonResult recycleOpinionBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleOpinionBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //回收意见并清除回收列表缓存
            int result = opinionService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("opinion:" + id);
            }
            //清除正常列表的缓存
            opinionService.clearCache(KEYLIST);
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

    //将回收的意见批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleOpinionBatch")
    public JsonResult recoveryRecycleOpinionBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleOpinionBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //恢复意见并清除回收列表缓存
            int result = opinionService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("opinion:" + id);
            }
            //清除正常列表的缓存
            opinionService.clearCache(KEYLIST);
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

    //批量删除意见
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteOpinionBatch")
    public JsonResult deleteOpinionBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteOpinionBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //删除意见并清除回收列表缓存
            int result = opinionService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("opinion:" + id);
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
