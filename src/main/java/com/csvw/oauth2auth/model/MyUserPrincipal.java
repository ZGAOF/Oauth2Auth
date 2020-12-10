package com.csvw.oauth2auth.model;

import com.csvw.oauth2auth.utils.UserRolePermissionUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author lk
 */
public class MyUserPrincipal implements UserDetails {


    private SysUser sysUser;
    private UserRolePermissionUtil userRolePermissionUtil;

    public MyUserPrincipal(SysUser sysUser, UserRolePermissionUtil userRolePermissionUtil) {
        this.sysUser = sysUser;
        this.userRolePermissionUtil = userRolePermissionUtil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRolePermissionUtil.roleQuery(sysUser);
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
