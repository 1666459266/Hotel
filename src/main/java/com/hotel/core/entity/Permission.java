package com.hotel.core.entity;

import java.io.Serializable;
import java.util.List;

public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String permissionName;

    private String permissionDescribe;

    private String permissionKey;

    private String permissionValue;

    private Integer parentPermissionId;

    private Integer parentSort;

    private Integer permissionStates;

    private List<Permission> permissions;

    private String test1;

    private String test2;

    private String test3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionDescribe() {
        return permissionDescribe;
    }

    public void setPermissionDescribe(String permissionDescribe) {
        this.permissionDescribe = permissionDescribe == null ? null : permissionDescribe.trim();
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey == null ? null : permissionKey.trim();
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue == null ? null : permissionValue.trim();
    }

    public Integer getParentPermissionId() {
        return parentPermissionId;
    }

    public void setParentPermissionId(Integer parentPermissionId) {
        this.parentPermissionId = parentPermissionId;
    }

    public Integer getParentSort() {
        return parentSort;
    }

    public void setParentSort(Integer parentSort) {
        this.parentSort = parentSort;
    }

    public Integer getPermissionStates() {
        return permissionStates;
    }

    public void setPermissionStates(Integer permissionStates) {
        this.permissionStates = permissionStates;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}