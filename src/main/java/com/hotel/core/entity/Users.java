package com.hotel.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String truename;

    private String gender;

    private String nation;

    private String address;

    private Date birthday;

    private String phone;

    private String email;

    private Integer microblogAccountStates;

    private Integer qqAccountStates;

    private Integer wechatAccountStates;

    private Integer alipayAccountStates;

    private Integer baiduAccountStates;

    private String idcardFront;

    private String idcardBack;

    private String idcardNumber;

    private Date registrationDate;

    private String membershipScore;

    private Integer membershipLevel;

    private Integer realnameAuthenticationStates;

    private Integer states;

    private String test1;

    private String test2;

    private String test3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getMicroblogAccountStates() {
        return microblogAccountStates;
    }

    public void setMicroblogAccountStates(Integer microblogAccountStates) {
        this.microblogAccountStates = microblogAccountStates;
    }

    public Integer getQqAccountStates() {
        return qqAccountStates;
    }

    public void setQqAccountStates(Integer qqAccountStates) {
        this.qqAccountStates = qqAccountStates;
    }

    public Integer getWechatAccountStates() {
        return wechatAccountStates;
    }

    public void setWechatAccountStates(Integer wechatAccountStates) {
        this.wechatAccountStates = wechatAccountStates;
    }

    public Integer getAlipayAccountStates() {
        return alipayAccountStates;
    }

    public void setAlipayAccountStates(Integer alipayAccountStates) {
        this.alipayAccountStates = alipayAccountStates;
    }

    public Integer getBaiduAccountStates() {
        return baiduAccountStates;
    }

    public void setBaiduAccountStates(Integer baiduAccountStates) {
        this.baiduAccountStates = baiduAccountStates;
    }

    public String getIdcardFront() {
        return idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront == null ? null : idcardFront.trim();
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack == null ? null : idcardBack.trim();
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber == null ? null : idcardNumber.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getMembershipScore() {
        return membershipScore;
    }

    public void setMembershipScore(String membershipScore) {
        this.membershipScore = membershipScore == null ? null : membershipScore.trim();
    }

    public Integer getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(Integer membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public Integer getRealnameAuthenticationStates() {
        return realnameAuthenticationStates;
    }

    public void setRealnameAuthenticationStates(Integer realnameAuthenticationStates) {
        this.realnameAuthenticationStates = realnameAuthenticationStates;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
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