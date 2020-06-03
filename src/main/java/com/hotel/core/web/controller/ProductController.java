package com.hotel.core.web.controller;

import com.hotel.core.entity.Product;
import com.hotel.core.entity.Users;
import com.hotel.core.service.ProductService;
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
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    //添加商品
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/insertProduct")
    public JsonResult insertProduct(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                    HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...insertProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            String productName = ToolUtil.str("productName",request);
            String productIntro = ToolUtil.str("productIntro",request);
            String productType = ToolUtil.str("productType",request);
            String productUnitPrice = ToolUtil.str("productUnitPrice",request);
            Integer productNum = ToolUtil.integer("productNum",request);
            if ((ToolUtil.equalBool(productName)&&ToolUtil.equalBool(productType)&&ToolUtil.equalBool(productUnitPrice)&&ToolUtil.equalBool(productNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Product product = new Product();
            product.setProductName(productName);
            if (multipartFile != null){
                //上传图片
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String productPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + productPicture);
                if (productPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                product.setProductPicture(productPicture);
            }
            product.setProductIntro(productIntro);
            product.setProductType(productType);
            product.setProductUnitPrice(productUnitPrice);
            product.setProductNum(productNum);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            product.setCreaterId(users.getId());
            product.setCreaterUsername(users.getUsername());
            product.setCreateTime(new Date());
            //如果商品数量未0 则显示无货 否则显示有货
            if (productNum == 0){
                product.setProductInstock(2);
            } else{
                product.setProductInstock(1);
            }
            product.setProductStates(1);
            //执行添加并清除正常列表的缓存
            int result = productService.insertProduct(product, KEYLIST);
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

    //修改商品
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/updateProduct")
    public JsonResult updateProduct(@RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                    HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...updateProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            String productName = ToolUtil.str("productName",request);
            String productIntro = ToolUtil.str("productIntro",request);
            String productType = ToolUtil.str("productType",request);
            String productUnitPrice = ToolUtil.str("productUnitPrice",request);
            Integer productNum = ToolUtil.integer("productNum",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            if ((ToolUtil.equalBool(productName)&&ToolUtil.equalBool(productType)&&ToolUtil.equalBool(productUnitPrice)&&ToolUtil.equalBool(productNum)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            Product product = productService.selectProductById(id);
            if (product == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该商品不存在");
                return jsonResult;
            }
            product.setProductName(productName);
            if (multipartFile != null){
                //上传图片
                String msg = "";
                FileUploadTool fileUploadTool = new FileUploadTool();
                String productPicture = fileUploadTool.createFile(multipartFile,request);
                System.out.println("URL:" + productPicture);
                if (productPicture != null) {
                    msg = "success";
                } else {
                    msg = "fail";
                }
                product.setProductPicture(productPicture);
            }
            product.setProductIntro(productIntro);
            product.setProductType(productType);
            product.setProductUnitPrice(productUnitPrice);
            product.setProductNum(productNum);
            //获取当前登录的用户名
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            Users users = usersService.selectUserByUsername(username);
            product.setModifierId(users.getId());
            product.setModifierUsername(users.getUsername());
            product.setModifyTime(new Date());
            //如果商品数量未0 则显示无货 否则显示有货
            if (productNum == 0){
                product.setProductInstock(2);
            } else{
                product.setProductInstock(1);
            }
            //执行修改并清除正常列表的缓存
            int result = productService.updateProduct(product, KEYLIST);
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

    //回收商品
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleProduct")
    public JsonResult recycleProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Product product = productService.selectProductById(id);
            if (product == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该商品不存在");
                return jsonResult;
            }
            product.setProductStates(2);
            //执行回收并清除回收列表的缓存
            int result = productService.updateProductByStates(product, RECYCLEKEYLIST);
            //清除正常列表的缓存
            productService.clearCache(KEYLIST);
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

    //将回收的商品恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleProduct")
    public JsonResult recoveryRecycleProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Product product = productService.selectProductById(id);
            if (product == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该商品不存在");
                return jsonResult;
            }
            product.setProductStates(1);
            //执行恢复并清除回收列表的缓存
            int result = productService.updateProductByStates(product, RECYCLEKEYLIST);
            //清除正常列表的缓存
            productService.clearCache(KEYLIST);
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
    @RequestMapping("/deleteProduct")
    public JsonResult deleteProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteProduct");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Product product = productService.selectProductById(id);
            if (product == null) {
                jsonResult = JsonResult.build(FLAG_FAILED,"该商品不存在");
                return jsonResult;
            }
            //执行删除并清除回收列表的缓存
            int result = productService.deleteProductById(id,RECYCLEKEYLIST);
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

    //批量回收商品
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recycleProductBatch")
    public JsonResult recycleProductBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recycleProductBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //回收商品并清除回收列表缓存
            int result = productService.updateStatesBatch(ids, 2,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("product::" + id);
            }
            //清除正常列表的缓存
            productService.clearCache(KEYLIST);
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

    //将回收的商品批量恢复到正常状态
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/recoveryRecycleProductBatch")
    public JsonResult recoveryRecycleProductBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...recoveryRecycleProductBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //恢复商品并清除回收列表缓存
            int result = productService.updateStatesBatch(ids, 1,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("product::" + id);
            }
            //清除正常列表的缓存
            productService.clearCache(KEYLIST);
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

    //批量删除商品
    @RequiresRoles(value = {"admin","user"},logical = Logical.AND)
    @RequestMapping("/deleteProductBatch")
    public JsonResult deleteProductBatch(@RequestBody List ids, HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...deleteProductBatch");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            //删除商品并清除回收列表缓存
            int result = productService.deleteBatch(ids,RECYCLEKEYLIST);
            //遍历ids集合
            for (Object id : ids){
                //清除缓存
                redisUtils.delete("product::" + id);
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
