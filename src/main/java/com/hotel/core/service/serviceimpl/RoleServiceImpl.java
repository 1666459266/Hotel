package com.hotel.core.service.serviceimpl;

import com.hotel.core.entity.Role;
import com.hotel.core.mapper.RoleMapper;
import com.hotel.core.mapper.RolePermissionMapper;
import com.hotel.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Caching(evict = {@CacheEvict(cacheNames = "role",key = "#role.id"),
                      @CacheEvict(cacheNames = "roleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roleCounts",key = "#KeyList")})
    @Override
    public int insertRole(Role role,String KeyList) {
        System.out.println("启用Service...insertRole");
        return roleMapper.insertSelective(role);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "role",key = "#role.id"),
                      @CacheEvict(cacheNames = "roleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roleCounts",key = "#KeyList")})
    @Override
    public int updateRole(Role role,String KeyList) {
        System.out.println("启用Service...updateRole");
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "role",key = "#id"),
                      @CacheEvict(cacheNames = "roleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roleCounts",key = "#KeyList")})
    @Override
    public int deleteRoleById(Integer id,String KeyList) {
        System.out.println("启用Service...deleteRoleById");
        int i = 0;
        int j = 0;
        try {
            i = roleMapper.deleteByPrimaryKey(id);
            j = rolePermissionMapper.deleteRolePermissionByRoleId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i+j;
    }

    @Cacheable(cacheNames = "role",key = "#id")
    @Override
    public Role selectRoleById(Integer id) {
        System.out.println("启用Service...selectRoleById");
        return roleMapper.selectByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "roleNameByUsername",key = "#username")
    @Override
    public Set<String> selectRoleNameByUsername(String username) {
        System.out.println("启用Service...selectRoleNameByUsername");
        return roleMapper.selectRoleNameByUsername(username);
    }

    @Override
    public List<Role> selectRoleListByLimit(Map<String, Object> map,String KeyList) {
        System.out.println("启用Service...selectRoleListByLimit");
        return roleMapper.selectRoleListByLimit(map);
    }

    @Override
    public int selectCounts(Integer states,String KeyList) {
        System.out.println("启用Service...selectCounts");
        return roleMapper.selectCounts(states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "role",key = "#role.id"),
                      @CacheEvict(cacheNames = "roleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roleCounts",key = "#KeyList")})
    @Override
    public int updateRoleByStates(Role role,String KeyList) {
        System.out.println("启用Service...updateRoleByStates");
        return roleMapper.updateRoleByStates(role);
    }

    @Override
    public List<Role> searchRole(String roleName, String roleDescription) {
        System.out.println("启用Service...searchRole");
        return roleMapper.searchRole(roleName,roleDescription);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "roleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roleCounts",key = "#KeyList")})
    @Override
    public int updateStatesBatch(List list,Integer states,String KeyList) {
        System.out.println("启用Service...updateStatesBatch");
        return roleMapper.updateStatesBatch(list,states);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "roleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roleCounts",key = "#KeyList")})
    @Override
    public int deleteBatch(List list,String KeyList) {
        System.out.println("启用Service...deleteBatch");
        return roleMapper.deleteBatch(list);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "roleList",key = "#KeyList"),
                      @CacheEvict(cacheNames = "roleCounts",key = "#KeyList")})
    @Override
    public void clearCache(String KeyList) {
        System.out.println("启用Service...clearCache");
    }

}
