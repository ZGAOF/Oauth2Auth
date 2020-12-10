package com.csvw.oauth2auth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sys_user")
public class SysUser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(targetEntity = SysRole.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role",
                //当前对象表在中间表里的外键
                joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")},
                //对方表在中间表里的外键
                inverseJoinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")}
                )
    private List<SysRole> roleSet = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(List<SysRole> roleSet) {
        this.roleSet = roleSet;
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
