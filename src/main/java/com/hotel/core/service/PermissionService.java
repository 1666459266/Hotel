package com.hotel.core.service;

import com.hotel.core.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    //添加资源权限
    public int insertPermission(Permission permission,String KeyList);

    //修改资源权限
    public int updatePermission(Permission permission,String KeyList);

    //根据id删除资源权限
    public int deletePermissionById(Integer id,String KeyList);

    //根据id查询资源权限
    public Permission selectPermissionById(Integer id);

    //查询所有资源权限
    public List<Permission> selectPermissionBySortAsc(String KeyList);

    //获取资源权限分页列表
    public List<Permission> selectPermissionListBySortAscLimit(Map<String,Object> map,String KeyList);

    //统计条数
    public int selectCounts(Integer states,String KeyList);

    //修改资源权限状态
    public int updatePermissionByStates(Permission permission,String KeyList);

    //根据角色名查询分级菜单
    public List<Permission> getPermissionByRoleName(String roleName);

    //搜索权限（模糊查询）
    public List<Permission> searchPermission(String permissionName,String permissionDescribe);

    //批量修改状态
    public int updateStatesBatch(List list,Integer states,String KeyList);

    //根据角色id查询资源权限
    List<Permission> selectPermissionListByRoleId(Integer roleId);

    //批量删除
    public int deleteBatch(List list,String KeyList);

    //清空缓存
    public void clearCache(String KeyList);

}
