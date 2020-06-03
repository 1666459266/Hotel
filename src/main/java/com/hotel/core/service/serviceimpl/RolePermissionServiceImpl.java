package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Permission;
import com.hotel.core.entity.Role;
import com.hotel.core.entity.RolePermission;
import com.hotel.core.mapper.PermissionMapper;
import com.hotel.core.mapper.RoleMapper;
import com.hotel.core.mapper.RolePermissionMapper;
import com.hotel.core.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "rolePermission",key = "#rolePermission.id"),
                      @CacheEvict(cacheNames = "rolePermissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "rolePermissionCounts",key = "#KeyList")})
    @Override
    public int insertRolePermission(RolePermission rolePermission, String KeyList) {
        System.out.println("启用Service...insertRolePermission");
        //根据传入的roleId和permissionId查询角色和资源权限
        Role role = roleMapper.selectByPrimaryKey(rolePermission.getRoleId());
        Permission permission = permissionMapper.selectByPrimaryKey(rolePermission.getPermissionId());
        //存入角色权限资源
        rolePermission.setRoleId(role.getId());
        rolePermission.setPermissionId(permission.getId());
        rolePermission.setRoleName(role.getRoleName());
        rolePermission.setRoleDescribe(role.getRoleDescription());
        rolePermission.setPermissionName(permission.getPermissionName());
        rolePermission.setPermissionDescribe(permission.getPermissionDescribe());
        return rolePermissionMapper.insertSelective(rolePermission);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "rolePermission",key = "#rolePermission.id"),
                      @CacheEvict(cacheNames = "rolePermissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "rolePermissionCounts",key = "#KeyList")})
    @Override
    public int updateRolePermission(RolePermission rolePermission, String KeyList) {
        System.out.println("启用Service...updateRolePermission");
        //根据传入的roleId和permissionId查询角色和资源权限
        Role role = roleMapper.selectByPrimaryKey(rolePermission.getRoleId());
        Permission permission = permissionMapper.selectByPrimaryKey(rolePermission.getPermissionId());
        //存入角色权限资源
        rolePermission.setRoleId(role.getId());
        rolePermission.setPermissionId(permission.getId());
        rolePermission.setRoleName(role.getRoleName());
        rolePermission.setRoleDescribe(role.getRoleDescription());
        rolePermission.setPermissionName(permission.getPermissionName());
        rolePermission.setPermissionDescribe(permission.getPermissionDescribe());
        return rolePermissionMapper.updateByPrimaryKeySelective(rolePermission);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "rolePermission",key = "#id"),
                      @CacheEvict(cacheNames = "rolePermissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "rolePermissionCounts",key = "#KeyList")})
    @Override
    public int deleteRolePermissionById(Integer id, String KeyList) {
        System.out.println("启用Service...deleteRolePermissionById");
        return rolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "rolePermission",key = "#id")
    @Override
    public RolePermission selectRolePermissionById(Integer id) {
        System.out.println("启用Service...selectRolePermissionById");
        return rolePermissionMapper.selectByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "rolePermissionList",key = "#KeyList")
    @Override
    public List<RolePermission> selectRolePermissionList(String KeyList) {
        System.out.println("启用Service...selectRolePermissionList");
        return rolePermissionMapper.selectRolePermissionList();
    }

    @Cacheable(cacheNames = "rolePermissionCounts",key = "#KeyList")
    @Override
    public int selectCounts(String KeyList) {
        System.out.println("启用Service...selectCounts");
        return rolePermissionMapper.selectCounts();
    }

    @Caching(evict = {@CacheEvict(cacheNames = "rolePermissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "rolePermissionCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return rolePermissionMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "rolePermissionList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "rolePermissionCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
