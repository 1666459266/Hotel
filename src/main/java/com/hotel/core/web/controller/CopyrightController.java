package com.hotel.core.web.controller;

import com.hotel.core.entity.Copyright;
import com.hotel.core.entity.Users;
import com.hotel.core.service.CopyrightService;
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
@RequestMapping("copyright")
public class CopyrightController extends BaseController {

    @Autowired
    private CopyrightService copyrightService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加版权信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertCopyright")
    public JsonResult insertCopyright(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertCopyright");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            String contactAddress = ToolUtil.str("contactAddress", request);
            String contactNumber = ToolUtil.str("contactNumber", request);
            Copyright copyright = new Copyright();
            copyright.setContactAddress(contactAddress);
            copyright.setContactNumber(contactNumber);
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            copyright.setCreaterId(users.getId());
            copyright.setCreaterUsername(users.getUsername());
            copyright.setCreateTime(new Date());
            copyright.setExistStates(1);
            //执行并添加缓存列表
            int result = copyrightService.insertCopyright(copyright, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED, e.getMessage());
        }
        return jsonResult;
    }

    //修改版权信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateCopyright")
    public JsonResult updateCopyright(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateCopyright");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id", request);
            String contactAddress = ToolUtil.str("contactAddress", request);
            String contactNumber = ToolUtil.str("contactNumber", request);
            if (ToolUtil.equalBool(id) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED, "id不能为空");
                return jsonResult;
            }
            Copyright copyright = copyrightService.selectCopyrightById(id);
            if (copyright == null) {
                jsonResult = JsonResult.build(FLAG_FAILED, "该信息不存在");
                return jsonResult;
            }
            copyright.setContactAddress(contactAddress);
            copyright.setContactNumber(contactNumber);
            //获取当前登陆的用户
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            copyright.setModifierId(users.getId());
            copyright.setModifierUsername(users.getUsername());
            copyright.setModifyTime(new Date());
            //执行修改并清除正常列表的缓存
            int result = copyrightService.updateCopyright(copyright, KEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED, e.getMessage());
        }
        return jsonResult;
    }
    //回收信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleCopyright")
    public JsonResult recycleCopyright(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleCopyright");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Copyright copyright = copyrightService.selectCopyrightById(id);
            if (copyright == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该信息不存在");
                return jsonResult;
            }
            copyright.setExistStates(2);
            //先清除正常列表的缓存
            copyrightService.updateCopyrightByStates(copyright,KEYLIST);
            //执行回收并清除回收列表的缓存
            int result = copyrightService.updateCopyrightByStates(copyright, RECYCLEKEYLIST);
            if (result > 0) {
                jsonResult = JsonResult.build(FLAG_SUCCESS);
            } else {
                jsonResult = JsonResult.build(FLAG_FAILED);
            }
        }catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //将回收的商品恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleRecycleCopyright")
    public JsonResult recoveryCopyrightProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryCopyrightProduct");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Copyright copyright = copyrightService.selectCopyrightById(id);
            if (copyright == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该信息不存在");
                return jsonResult;
            }
            copyright.setExistStates(1);
            //先清除正常列表的缓存
            copyrightService.updateCopyrightByStates(copyright,KEYLIST);
            //执行恢复并清除回收列表的缓存
            int result = copyrightService.updateCopyrightByStates(copyright, RECYCLEKEYLIST);
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

    //删除商品
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteCopyright")
    public JsonResult deleteCopyright(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteCopyright");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Copyright copyright = copyrightService.selectCopyrightById(id);
            if (copyright == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该信息不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = copyrightService.deleteCopyrightById(id,RECYCLEKEYLIST);
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

    //批量回收版权信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleCopyrightBatch")
    public JsonResult recycleCopyrightBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleCopyrightBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //回收商品并清除回收列表缓存
            int result = copyrightService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("copyright:" + id);
            }
            //清除正常列表的缓存
            copyrightService.clearCache(KEYLIST);
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

    //将回收的版权信息批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleCopyrightBatch")
    public JsonResult recoveryRecycleCopyrightBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleCopyrightBatch");
        redisUtils.setDataBase(3);
        System.out.println("Redis切换到[3]库");
        try {
            //恢复商品并清除回收列表缓存
            int result = copyrightService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("copyright:" + id);
            }
            //清除正常列表的缓存
            copyrightService.clearCache(KEYLIST);
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

    //批量删除版权信息
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteCopyrightBatch")
    public JsonResult deleteCopyrightBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteCopyrightBatch");
        redisUtils.setDataBase(2);
        System.out.println("Redis切换到[2]库");
        try {
            //删除商品并清除回收列表缓存
            int result = copyrightService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("copyright:" + id);
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
