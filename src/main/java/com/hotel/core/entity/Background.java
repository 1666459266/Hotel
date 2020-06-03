package com.hotel.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Background implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String backgroundPicture;

    private String backgroundIntro;

    private Integer createrId;

    private String createrUsername;

    private Date createTime;

    private Integer modifierId;

    private String modifierUsername;

    private Date modifyTime;

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

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture == null ? null : backgroundPicture.trim();
    }

    public String getBackgroundIntro() {
        return backgroundIntro;
    }

    public void setBackgroundIntro(String backgroundIntro) {
        this.backgroundIntro = backgroundIntro == null ? null : backgroundIntro.trim();
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