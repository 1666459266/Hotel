package com.hotel.core.mapper;

import com.hotel.core.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Permission permission);

    int insertSelective(Permission permission);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission permission);

    int updateByPrimaryKey(Permission permission);

    //查询所有资源权限
    List<Permission> selectPermissionBySortAsc();

    //获取资源权限分页列表
    List<Permission> selectPermissionListBySortAscLimit(Map<String,Object> map);

    //统计条数
    int selectCounts(Integer states);

    //修改资源权限状态
    int updatePermissionByStates(Permission permission);

    //根据角色名查询父菜单
    List<Permission> getPermissionByRoleName(String roleName);

    //根据父菜单 返回所有子菜单
    List<Permission> findMenuByParentId(Integer id);

    //搜索权限（模糊查询）
    List<Permission> searchPermission(String permissionName,String permissionDescribe);

    //根据角色id查询资源权限
    List<Permission> selectPermissionListByRoleId(Integer roleId);

    //批量修改状态
    int updateStatesBatch(List list,Integer states);

    //批量删除
    int deleteBatch(List list);

}