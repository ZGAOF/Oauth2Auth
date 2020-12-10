package com.csvw.oauth2auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sys_permission")
public class SysPermission {
    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(name = "authority")
    private String authority;

    @JsonIgnore
    @ManyToMany(mappedBy = "sysPermissionSet")
    private List<SysRole> sysRoleSet = new ArrayList<>();

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<SysRole> getSysRoleSet() {
        return sysRoleSet;
    }

    public void setSysRoleSet(List<SysRole> sysRoleSet) {
        this.sysRoleSet = sysRoleSet;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
