package com.hotel.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private String username;

    private String couponPicture;

    private String couponPrice;

    private String couponDetails;

    private String usableRange;

    private String serviceConditions;

    private Date getDate;

    private Date expirationDate;

    private Integer couponStates;

    private Integer usageStates;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCouponPicture() {
        return couponPicture;
    }

    public void setCouponPicture(String couponPicture) {
        this.couponPicture = couponPicture == null ? null : couponPicture.trim();
    }

    public String getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(String couponPrice) {
        this.couponPrice = couponPrice == null ? null : couponPrice.trim();
    }

    public String getCouponDetails() {
        return couponDetails;
    }

    public void setCouponDetails(String couponDetails) {
        this.couponDetails = couponDetails == null ? null : couponDetails.trim();
    }

    public String getUsableRange() {
        return usableRange;
    }

    public void setUsableRange(String usableRange) {
        this.usableRange = usableRange == null ? null : usableRange.trim();
    }

    public String getServiceConditions() {
        return serviceConditions;
    }

    public void setServiceConditions(String serviceConditions) {
        this.serviceConditions = serviceConditions == null ? null : serviceConditions.trim();
    }

    public Date getGetDate() {
        return getDate;
    }

    public void setGetDate(Date getDate) {
        this.getDate = getDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCouponStates() {
        return couponStates;
    }

    public void setCouponStates(Integer couponStates) {
        this.couponStates = couponStates;
    }

    public Integer getUsageStates() {
        return usageStates;
    }

    public void setUsageStates(Integer usageStates) {
        this.usageStates = usageStates;
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