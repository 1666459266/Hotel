package com.hotel.core.service;

import com.hotel.core.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    //新增角色资源权限
    public int insertRolePermission(RolePermission rolePermission,String KeyList);

    //修改角色资源权限
    public int updateRolePermission(RolePermission rolePermission,String KeyList);

    //根据id删除角色资源权限
    public int deleteRolePermissionById(Integer id,String KeyList);

    //根据id查询角色资源权限
    public RolePermission selectRolePermissionById(Integer id);

    //查询所有角色资源权限
    public List<RolePermission> selectRolePermissionList(String KeyList);

    //统计条数
    public int selectCounts(String KeyList);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
