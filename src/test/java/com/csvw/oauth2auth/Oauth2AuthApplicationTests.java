package com.csvw.oauth2auth;

import com.csvw.oauth2auth.model.SysPermission;
import com.csvw.oauth2auth.model.SysRole;
import com.csvw.oauth2auth.model.SysUser;
import com.csvw.oauth2auth.repository.PermissionRepo;
import com.csvw.oauth2auth.repository.RoleRepo;
import com.csvw.oauth2auth.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Oauth2AuthApplicationTests {

    //为何此处不行，须换BCrypt?
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testDataGenerator(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("Leo");
        sysUser.setPassword("123");
        SysUser sysUser1 = new SysUser();
        sysUser1.setUsername("Tom");
        sysUser1.setPassword("123");

        SysRole sysRole = new SysRole();
        sysRole.setRole("ROLE_ADMIN");
        SysRole sysRole1 = new SysRole();
        sysRole1.setRole("ROLE_USER");

        SysPermission sysPermission = new SysPermission();
        sysPermission.setAuthority("ALL_PERMISSION");
        SysPermission sysPermission1 = new SysPermission();
        sysPermission1.setAuthority("READ");
        SysPermission sysPermission2 = new SysPermission();
        sysPermission2.setAuthority("WRITE");

        //两方同时维护中间表会报错，同一时间只能有一方来维护
        //这个时候谁放弃维护权？被选择的一方放弃维护权(mappedBy)

        sysRole.getSysPermissionSet().add(sysPermission);
        sysRole.getSysPermissionSet().add(sysPermission1);
        sysRole.getSysPermissionSet().add(sysPermission2);
        sysRole1.getSysPermissionSet().add(sysPermission1);
        sysRole1.getSysPermissionSet().add(sysPermission2);

        sysUser.getRoleSet().add(sysRole);
        sysUser.getRoleSet().add(sysRole1);
        sysUser1.getRoleSet().add(sysRole1);

        userRepo.save(sysUser);
        userRepo.save(sysUser1);
        roleRepo.save(sysRole);
        roleRepo.save(sysRole1);
        permissionRepo.save(sysPermission);
        permissionRepo.save(sysPermission1);
        permissionRepo.save(sysPermission2);

    }

	@Test
	public void testSysUser(){
        System.out.println("!@#$%^&*()_+----> "+new BCryptPasswordEncoder().encode("123"));

    }
}
