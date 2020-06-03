package com.hotel.core.web.controller;

import com.hotel.core.entity.Fitness;
import com.hotel.core.entity.Users;
import com.hotel.core.service.FitnessService;
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
@RequestMapping("fitness")
public class FitnessController extends BaseController {

    @Autowired
    private FitnessService fitnessService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加健身器材
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertFitness")
    public JsonResult insertFitness(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                    HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertFitness");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            String equipmentName = ToolUtil.str("equipmentName",request);
            String equipmentIntro = ToolUtil.str("equipmentIntro",request);
            Integer equipmentNum = ToolUtil.integer("equipmentNum",request);
            String equipmentUsage = ToolUtil.str("equipmentUsage",request);
            if ((ToolUtil.equalBool(equipmentName)&&ToolUtil.equalBool(equipmentNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Fitness fitness = new Fitness();
            fitness.setEquipmentName(equipmentName);
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String equipmentPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + equipmentPicture);
                if (equipmentPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                fitness.setEquipmentPicture(equipmentPicture);
            }
            fitness.setEquipmentIntro(equipmentIntro);
            fitness.setEquipmentNum(equipmentNum);
            fitness.setEquipmentUsage(equipmentUsage);
            fitness.setEquipmentStates(1);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            fitness.setCreaterId(users.getId());
            fitness.setCreaterUsername(users.getUsername());
            fitness.setCreateTime(new Date());
            fitness.setExistStates(1);
            //执行添加并清除正常列表的缓存
            int result = fitnessService.insertFitness(fitness, KEYLIST);
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

    //修改器材
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateFitness")
    public JsonResult updateFitness(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                    HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateFitness");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String equipmentName = ToolUtil.str("equipmentName",request);
            String equipmentIntro = ToolUtil.str("equipmentIntro",request);
            Integer equipmentNum = ToolUtil.integer("equipmentNum",request);
            String equipmentUsage = ToolUtil.str("equipmentUsage",request);
            Integer equipmentStates = ToolUtil.integer("equipmentStates",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(equipmentName)&&ToolUtil.equalBool(equipmentNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Fitness fitness = fitnessService.selectFitnessById(id);
            if (fitness == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该器材不存在");
                return jsonResult;
            }
            fitness.setEquipmentName(equipmentName);
            if (multipartFile != null) {
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String equipmentPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + equipmentPicture);
                if (equipmentPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                fitness.setEquipmentPicture(equipmentPicture);
            }
            fitness.setEquipmentIntro(equipmentIntro);
            fitness.setEquipmentNum(equipmentNum);
            fitness.setEquipmentUsage(equipmentUsage);
            fitness.setEquipmentStates(equipmentStates);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            fitness.setModifierId(users.getId());
            fitness.setModifierUsername(users.getUsername());
            fitness.setModifyTime(new Date());
            //执行修改并清除正常列表的缓存
            int result = fitnessService.updateFitness(fitness, KEYLIST);
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

    //回收器材
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleFitness")
    public JsonResult recycleFitness(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleFitness");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Fitness fitness = fitnessService.selectFitnessById(id);
            if (fitness == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该器材不存在");
                return jsonResult;
            }
            fitness.setExistStates(2);
            //先清除正常列表的缓存
            fitnessService.updateFitnessByStates(fitness,KEYLIST);
            //执行回收并清除回收列表的缓存
            int result = fitnessService.updateFitnessByStates(fitness, RECYCLEKEYLIST);
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

    //将回收的健身器材恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleFitness")
    public JsonResult recoveryRecycleFitness(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleFitness");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Fitness fitness = fitnessService.selectFitnessById(id);
            if (fitness == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该器材不存在");
                return jsonResult;
            }
            fitness.setExistStates(1);
            //先清除正常列表的缓存
            fitnessService.updateFitnessByStates(fitness,KEYLIST);
            //执行恢复并清除回收列表的缓存
            int result = fitnessService.updateFitnessByStates(fitness, RECYCLEKEYLIST);
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

    //删除健身器材
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteFitness")
    public JsonResult deleteFitness(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteFitness");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Fitness fitness = fitnessService.selectFitnessById(id);
            if (fitness == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该器材不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = fitnessService.deleteFitnessById(id,RECYCLEKEYLIST);
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

    //批量回收健身器材
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleFitnessBatch")
    public JsonResult recycleFitnessBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleFitnessBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //回收健身器材并清除回收列表缓存
            int result = fitnessService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("fitness:" + id);
            }
            //清除正常列表的缓存
            fitnessService.clearCache(KEYLIST);
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

    //将回收的健身器材批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleFitnessBatch")
    public JsonResult recoveryRecycleFitnessBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleFitnessBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //恢复健身器材并清除回收列表缓存
            int result = fitnessService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("fitness:" + id);
            }
            //清除正常列表的缓存
            fitnessService.clearCache(KEYLIST);
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

    //批量删除健身器材
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteFitnessBatch")
    public JsonResult deleteFitnessBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteFitnessBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //删除健身器材并清除回收列表缓存
            int result = fitnessService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("fitness:" + id);
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
