package com.csvw.oauth2auth.utils;

import com.csvw.oauth2auth.model.SysPermission;
import com.csvw.oauth2auth.model.SysRole;
import com.csvw.oauth2auth.model.SysUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author lk
 */
public interface UserRolePermissionUtil {

    SysUser userCascadeQuery(String username);

    List<SysRole> roleQuerySet(SysUser sysUser);

   Collection<SimpleGrantedAuthority> roleQuery(SysUser sysUser);

    List<SysPermission> permissionQuery(SysUser sysUser);

}