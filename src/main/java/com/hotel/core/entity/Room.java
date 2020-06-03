package com.hotel.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String roomFloor;

    private Integer roomNumber;

    private String roomPicture;

    private String roomIntro;

    private String roomType;

    private String roomMax;

    private String roomArea;

    private String roomBedType;

    private String roomPrice;

    private Integer createrId;

    private String createrUsername;

    private Date createTime;

    private Integer modifyId;

    private String modifyUsername;

    private Date modifyTime;

    private Integer roomStates;

    private Integer existStates;

    private String test1;

    private String test2;

    private String test3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(String roomFloor) {
        this.roomFloor = roomFloor == null ? null : roomFloor.trim();
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomPicture() {
        return roomPicture;
    }

    public void setRoomPicture(String roomPicture) {
        this.roomPicture = roomPicture == null ? null : roomPicture.trim();
    }

    public String getRoomIntro() {
        return roomIntro;
    }

    public void setRoomIntro(String roomIntro) {
        this.roomIntro = roomIntro == null ? null : roomIntro.trim();
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    public String getRoomMax() {
        return roomMax;
    }

    public void setRoomMax(String roomMax) {
        this.roomMax = roomMax == null ? null : roomMax.trim();
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea == null ? null : roomArea.trim();
    }

    public String getRoomBedType() {
        return roomBedType;
    }

    public void setRoomBedType(String roomBedType) {
        this.roomBedType = roomBedType == null ? null : roomBedType.trim();
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice == null ? null : roomPrice.trim();
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

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public String getModifyUsername() {
        return modifyUsername;
    }

    public void setModifyUsername(String modifyUsername) {
        this.modifyUsername = modifyUsername == null ? null : modifyUsername.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getRoomStates() {
        return roomStates;
    }

    public void setRoomStates(Integer roomStates) {
        this.roomStates = roomStates;
    }

    public Integer getExistStates() {
        return existStates;
    }

    public void setExistStates(Integer existStates) {
        this.existStates = existStates;
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