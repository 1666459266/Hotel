package com.hotel.core.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class UserRestaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private Integer restaurantId;

    private String username;

    private String orderNumber;

    private String foodName;

    private String foodUnitPrice;

    private Integer foodNum;

    private Date orderTime;

    private Integer orderOverdueStates;

    private Integer orderStates;

    private String test1;

    private String test2;

    private String test3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public String getFoodUnitPrice() {
        return foodUnitPrice;
    }

    public void setFoodUnitPrice(String foodUnitPrice) {
        this.foodUnitPrice = foodUnitPrice == null ? null : foodUnitPrice.trim();
    }

    public Integer getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(Integer foodNum) {
        this.foodNum = foodNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderOverdueStates() {
        return orderOverdueStates;
    }

    public void setOrderOverdueStates(Integer orderOverdueStates) {
        this.orderOverdueStates = orderOverdueStates;
    }

    public Integer getOrderStates() {
        return orderStates;
    }

    public void setOrderStates(Integer orderStates) {
        this.orderStates = orderStates;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1 == null ? null : test1.trim();
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2 == null ? null : test2.trim();
    }

    public String getTest3() {
        return test3;
    }

    public void setTest3(String test3) {
        this.test3 = test3 == null ? null : test3.trim();
    }


}
