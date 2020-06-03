package com.hotel.core.web.shiro;

import com.hotel.core.entity.Permission;
import com.hotel.core.service.PermissionService;
import com.hotel.core.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FilterChainDefinitionMapBuilder {

    @Autowired
    @Lazy
    private PermissionService permissionService;

    public Map<String,String> builder(){
        Map<String,String> map = new LinkedHashMap<>();
        List<Permission> permissions = permissionService.selectPermissionBySortAsc(BaseController.KEYLIST);
        for (Permission permission : permissions){
            map.put(permission.getPermissionKey(),permission.getPermissionValue());
        }
        return map;
    }

}
