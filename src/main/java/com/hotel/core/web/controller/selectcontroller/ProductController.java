package com.hotel.core.web.controller.selectcontroller;

import com.hotel.core.entity.Product;
import com.hotel.core.service.ProductService;
import com.hotel.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("selectProductController")
@RequestMapping("/select")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private RedisUtils redisUtils;

    //根据id获取商品信息
    @RequestMapping("/getProductById")
    public JsonResult getProductById(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getProductById");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
        try {
            Integer id = ToolUtil.integer("id",request);
            if (ToolUtil.equalBool(id) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"id不能为空");
                return jsonResult;
            }
            Product product = productService.selectProductById(id);
            jsonResult = JsonResult.build(FLAG_SUCCESS,product);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取正常状态的商品信息
    @RequestMapping("/getProductList")
    public JsonResult getProductList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getProductList");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
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
            map.put("productStates",1);
            String msg = "success";
            int states = 1;
            //执行查询并传入缓存的key
            List<Product> productList = productService.selectProductListByLimit(map, KEYLIST);
            int counts = productService.selectCounts(states, KEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,productList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //获取回收的商品信息
    @RequestMapping("/getRecycleProductList")
    public JsonResult getRecycleProductList(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getRecycleProductList");
        redisUtils.setDataBase(1);
        System.out.println("Redis切换到[1]库");
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
            map.put("productStates",2);
            String msg = "success";
            int states = 2;
            //执行查询并传入缓存的key
            List<Product> productList = productService.selectProductListByLimit(map, RECYCLEKEYLIST);
            int counts = productService.selectCounts(states, RECYCLEKEYLIST);
            jsonResult = JsonResult.build(FLAG_SUCCESS,productList,msg,counts);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //搜索商品（模糊查询）
    @RequestMapping("/searchProduct")
    public JsonResult searchProduct(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...searchProduct");
        try {
            String productName = ToolUtil.str("productName",request);
            if (ToolUtil.equalBool(productName) == false) {
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            List<Product> productList = productService.searchProduct(productName);
            jsonResult = JsonResult.build(FLAG_SUCCESS,productList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据商品热度降序排序
    @RequestMapping("/getProductListByProductPopularitySortDesc")
    public JsonResult getProductListByProductPopularitySortDesc(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getProductListByProductPopularitySortDesc");
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
            map.put("productStates",1);
            List<Product> productList = productService.selectProductListByProductPopularitySortDesc(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,productList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

    //根据商品分类筛选
    @RequestMapping("/getProductListByProductType")
    public JsonResult getProductListByProductType(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        System.out.println("启用Controller...getProductListByProductType");
        try {
            Map<String,Object> map = new HashMap<>();
            Integer page = ToolUtil.integer("page",request);
            Integer limit = ToolUtil.integer("limit",request);
            String productType = ToolUtil.str("productType",request);
            if ((ToolUtil.equalBool(page)&&ToolUtil.equalBool(limit)&&ToolUtil.equalBool(productType)) == false){
                jsonResult = JsonResult.build(FLAG_FAILED,"数据缺失");
                return jsonResult;
            }
            PageVO pageVO = new PageVO(page,limit);
            map.put("pages",pageVO.getBeginNum());
            map.put("limit",pageVO.getLimit());
            map.put("productType",productType);
            map.put("productStates",1);
            List<Product> productList = productService.selectProductListByProductType(map);
            jsonResult = JsonResult.build(FLAG_SUCCESS,productList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.build(FLAG_FAILED,e.getMessage());
        }
        return jsonResult;
    }

}
