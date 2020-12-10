package com.csvw.oauth2auth.utilsimpl;

import com.csvw.oauth2auth.model.SysPermission;
import com.csvw.oauth2auth.model.SysRole;
import com.csvw.oauth2auth.model.SysUser;
import com.csvw.oauth2auth.repository.UserRepo;
import com.csvw.oauth2auth.utils.UserRolePermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author lk
 */
@Component
public class UserRolePermissionUtilImpl implements UserRolePermissionUtil {

    @Autowired
    private UserRepo userRepo;

    @Override
    public SysUser userCascadeQuery(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<SysRole> roleQuerySet(SysUser sysUser) {
        List<SysRole> sysRoleSet = new ArrayList<>(16);
        sysRoleSet.addAll(sysUser.getRoleSet());
        return sysRoleSet;
    }

    @Override
    public Collection<SimpleGrantedAuthority> roleQuery(SysUser sysUser) {
        List<SysRole> sysRoleSet = roleQuerySet(sysUser);
        Collection<SimpleGrantedAuthority> roleGrantedSet = new HashSet<>(8);
        for (SysRole sysRole : sysRoleSet){
            String roleStr = sysRole.getRole();
            roleGrantedSet.add(new SimpleGrantedAuthority(roleStr));
        }
        return roleGrantedSet;
    }

    @Override
    public List<SysPermission> permissionQuery(SysUser sysUser) {
        List<SysPermission> sysPermissionSet = new ArrayList<>(64);
        List<SysRole> sysRoleSet = roleQuerySet(sysUser);
        if (sysRoleSet.size() != 0) {
            for (SysRole sysRole : sysRoleSet) {
                Set<SysPermission> sysPermissionsCache = (Set<SysPermission>) sysRole.getSysPermissionSet();
                if (sysPermissionsCache.size() != 0 ) {
                    sysPermissionSet.addAll(sysPermissionsCache);
                }
            }
            return sysPermissionSet;
        }else {
            return sysPermissionSet;
        }
    }
}








