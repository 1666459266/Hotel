package com.hotel.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String restaurantType;

    private String restaurantPicture;

    private String restaurantIntro;

    private String foodName;

    private String foodUnitPrice;

    private Integer foodPopularity;

    private Integer foodNum;

    private Date registerDate;

    private Integer createrId;

    private String createrUsername;

    private Date createTime;

    private Integer modifierId;

    private String modifierUsername;

    private Date modifyTime;

    private Integer foodInstock;

    private Integer foodStates;

    private String test1;

    private String test2;

    private String test3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType == null ? null : restaurantType.trim();
    }

    public String getRestaurantPicture() {
        return restaurantPicture;
    }

    public void setRestaurantPicture(String restaurantPicture) {
        this.restaurantPicture = restaurantPicture == null ? null : restaurantPicture.trim();
    }

    public String getRestaurantIntro() {
        return restaurantIntro;
    }

    public void setRestaurantIntro(String restaurantIntro) {
        this.restaurantIntro = restaurantIntro == null ? null : restaurantIntro.trim();
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

    public Integer getFoodPopularity() {
        return foodPopularity;
    }

    public void setFoodPopularity(Integer foodPopularity) {
        this.foodPopularity = foodPopularity;
    }

    public Integer getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(Integer foodNum) {
        this.foodNum = foodNum;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getCreaterUsername() {
        return createrUsername;
    }

    public void setCreaterUsername(String createrUsername) {
        this.createrUsername = createrUsername == null ? null : createrUsername.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierUsername() {
        return modifierUsername;
    }

    public void setModifierUsername(String modifierUsername) {
        this.modifierUsername = modifierUsername == null ? null : modifierUsername.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getFoodInstock() {
        return foodInstock;
    }

    public void setFoodInstock(Integer foodInstock) {
        this.foodInstock = foodInstock;
    }

    public Integer getFoodStates() {
        return foodStates;
    }

    public void setFoodStates(Integer foodStates) {
        this.foodStates = foodStates;
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