package com.csvw.oauth2auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roleSet")
    private List<SysUser> sysUserSet = new ArrayList<>();

    @ManyToMany(targetEntity = SysPermission.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_role_permission",
            //当前对象表在中间表里的外键
            joinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")},
            //对方表在中间表里的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_permission_id", referencedColumnName = "permission_id")}
    )
    private List<SysPermission> sysPermissionSet = new ArrayList<>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<SysUser> getSysUserSet() {
        return sysUserSet;
    }

    public void setSysUserSet(List<SysUser> sysUserSet) {
        this.sysUserSet = sysUserSet;
    }

    public List<SysPermission> getSysPermissionSet() {
        return sysPermissionSet;
    }

    public void setSysPermissionSet(List<SysPermission> sysPermissionSet) {
        this.sysPermissionSet = sysPermissionSet;
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
