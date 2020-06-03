package com.hotel.core.web.controller;

import com.hotel.core.entity.History;
import com.hotel.core.entity.Users;
import com.hotel.core.service.HistoryService;
import com.hotel.core.service.UsersService;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.JsonResult;
import com.hotel.core.utils.RedisUtils;
import com.hotel.core.utils.ToolUtil;
import com.hotel.core.utils.uploadfile.FileUploadTool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("history")
public class HistoryController extends BaseController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加历史
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertHistory")
    public JsonResult insertHistory(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                    HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertHistory");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            String historyIntro = ToolUtil.str("historyIntro",request);
            History history = new History();
            history.setHistoryIntro(historyIntro);
            if (multipartFile != null){
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String historyPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + historyPicture);
                if (historyPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                history.setHistoryPicture(historyPicture);
            }
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            history.setCreaterId(users.getId());
            history.setCreaterUsername(users.getUsername());
            history.setCreateTime(new Date());
            history.setExistStates(1);
            //执行添加并清除正常列表的缓存
            int result = historyService.insertHistory(history, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //修改历史
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateHistory")
    public JsonResult updateHistory(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                    HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateHistory");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String historyIntro = ToolUtil.str("historyIntro",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            History history = historyService.selectHistoryById(id);
            if (history == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"没有历史记录");
                return jsonResult;
            }
            history.setHistoryIntro(historyIntro);
            if (multipartFile != null){
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String historyPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + historyPicture);
                if (historyPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                history.setHistoryPicture(historyPicture);
            }
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            history.setModifierId(users.getId());
            history.setModifierUsername(users.getUsername());
            history.setModifyTime(new Date());
            //执行添加并清除正常列表的缓存
            int result = historyService.updateHistory(history, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (NullPointerException npe){
            npe.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,"文件不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //回收历史
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleHistory")
    public JsonResult recycleHistory(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleHistory");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            History history = historyService.selectHistoryById(id);
            if (history == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"没有历史记录");
                return jsonResult;
            }
            history.setExistStates(2);
            //先清除正常的缓存列表
            historyService.updateHistoryByStates(history,KEYLIST);
            //执行回收并清除回收列表的缓存
            int result = historyService.updateHistoryByStates(history, RECYCLEKEYLIST);
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

    //将回收的信息恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRecycleHistory")
    public JsonResult recycleRecycleHistory(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleRecycleHistory");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            History history = historyService.selectHistoryById(id);
            if (history == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"没有历史记录");
                return jsonResult;
            }
            history.setExistStates(1);
            //先清除正常的缓存列表
            historyService.updateHistoryByStates(history,KEYLIST);
            //执行恢复并清除回收列表的缓存
            int result = historyService.updateHistoryByStates(history, RECYCLEKEYLIST);
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
    @RequestMapping("/deleteHistory")
    public JsonResult deleteHistory(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteHistory");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            History history = historyService.selectHistoryById(id);
            if (history == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"没有历史记录");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = historyService.deleteHistoryById(id, RECYCLEKEYLIST);
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

    //批量回收酒店历史
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleHistoryBatch")
    public JsonResult recycleHistoryBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleHistoryBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //回收酒店历史并清除回收列表缓存
            int result = historyService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("history:" + id);
            }
            //清除正常列表的缓存
            historyService.clearCache(KEYLIST);
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

    //将回收的酒店历史批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleHistoryBatch")
    public JsonResult recoveryRecycleHistoryBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleHistoryBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //恢复酒店历史并清除回收列表缓存
            int result = historyService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("history:" + id);
            }
            //清除正常列表的缓存
            historyService.clearCache(KEYLIST);
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

    //批量删除酒店历史
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteHistoryBatch")
    public JsonResult deleteHistoryBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteHistoryBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //删除酒店历史并清除回收列表缓存
            int result = historyService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("history:" + id);
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
