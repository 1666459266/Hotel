package com.hotel.core.mapper;

import com.hotel.core.entity.RolePermission;

import java.util.List;

public interface RolePermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission rolePermission);

    int insertSelective(RolePermission rolePermission);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission rolePermission);

    int updateByPrimaryKey(RolePermission rolePermission);

    //查询所有角色资源权限
    List<RolePermission> selectRolePermissionList();

    //统计条数
    int selectCounts();

    //根据角色id删除资源权限(删除角色的同时删除资源权限)
    int deleteRolePermissionByRoleId(Integer roleId);

    //批量删除
    int deleteBatch(List list);

}