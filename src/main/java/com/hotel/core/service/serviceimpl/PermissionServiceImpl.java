package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Permission;
import com.hotel.core.mapper.PermissionMapper;
import com.hotel.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "permission",key = "#permission.id"),
                      @CacheEvict(cacheNames = "permissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permissionCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permission",key = "#KeyList")})
    @Override
    public int insertPermission(Permission permission,String KeyList) {
        System.out.println("启用Service...insertPermission");
        return permissionMapper.insertSelective(permission);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "permission",key = "#permission.id"),
                      @CacheEvict(cacheNames = "permissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permissionCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permission",key = "#KeyList")})
    @Override
    public int updatePermission(Permission permission,String KeyList) {
        System.out.println("启用Service...updatePermission");
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "permission",key = "#id"),
                      @CacheEvict(cacheNames = "permissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permissionCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permission",key = "#KeyList")})
    @Override
    public int deletePermissionById(Integer id,String KeyList) {
        System.out.println("启用Service...deletePermissionById");
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "permission",key = "#id")
    @Override
    public Permission selectPermissionById(Integer id) {
        System.out.println("启用Service...selectPermissionById");
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "permission",key = "#KeyList")
    @Override
    public List<Permission> selectPermissionBySortAsc(String KeyList) {
        System.out.println("启用Service...selectPermissionBySortAsc");
        return permissionMapper.selectPermissionBySortAsc();
    }

    @Override
    public List<Permission> selectPermissionListBySortAscLimit(Map<String, Object> map,String KeyList) {
        System.out.println("启用Service...selectPermissionListBySortAscLimit");
        return permissionMapper.selectPermissionListBySortAscLimit(map);
    }

    @Override
    public int selectCounts(Integer states,String KeyList) {
        System.out.println("启用Service...selectCounts");
        return permissionMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "permission",key = "#permission.id"),
                      @CacheEvict(cacheNames = "permissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permissionCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permission",key = "#KeyList")})
    @Override
    public int updatePermissionByStates(Permission permission,String KeyList) {
        System.out.println("启用Service...updatePermissionByStates");
        return permissionMapper.updatePermissionByStates(permission);
    }

    @Cacheable(cacheNames = "getPermissionByRoleName",key = "#roleName")
    @Override
    public List<Permission> getPermissionByRoleName(String roleName) {
        System.out.println("启用Service...getPermissionByRoleName");
        return permissionMapper.getPermissionByRoleName(roleName);
    }

    @Override
    public List<Permission> searchPermission(String permissionName, String permissionDescribe) {
        System.out.println("启用Service...searchPermission");
        return permissionMapper.searchPermission(permissionName,permissionDescribe);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "permissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permissionCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permission",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list,Integer states,String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return permissionMapper.updateStatesBatch(list,states);
    }

    @Override
    public List<Permission> selectPermissionListByRoleId(Integer roleId) {
        return permissionMapper.selectPermissionListByRoleId(roleId);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "permissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permissionCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permission",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return permissionMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "permissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permissionCounts",key = "#KeyList"),
                      @CacheEvict(cacheNames = "permission",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
