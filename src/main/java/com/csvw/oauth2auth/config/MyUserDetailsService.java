package com.csvw.oauth2auth.config;

import com.csvw.oauth2auth.model.MyUserPrincipal;
import com.csvw.oauth2auth.model.SysUser;
import com.csvw.oauth2auth.repository.UserRepo;
import com.csvw.oauth2auth.utilsimpl.UserRolePermissionUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRolePermissionUtilImpl userRolePermissionUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, ProviderNotFoundException {
        SysUser user = userRepo.findByUsername(username);
        SysUser sysUser = userRolePermissionUtil.userCascadeQuery(username);
        /*List<SysRole> sysRoleSet = userRolePermissionUtil.roleQuerySet(sysUser);
        if (sysUser==null){
            throw new UsernameNotFoundException("the user is not found");
        }else if (sysRoleSet.size()==0){
            throw new ProviderNotFoundException("the user has no role");
        }
        List<SysPermission> sysPermissionSet = userRolePermissionUtil.permissionQuery(sysUser);
        if (sysPermissionSet.size()==0){
            throw new ProviderNotFoundException("the user has no authority");
        }*/

        MyUserPrincipal myUserPrincipal = new MyUserPrincipal(sysUser, userRolePermissionUtil);
//        User user1 = new User(myUserPrincipal.getUsername(),myUserPrincipal.getPassword(),myUserPrincipal.isEnabled(),true,true,true, myUserPrincipal.getAuthorities());
        User user2 = new User(myUserPrincipal.getUsername(),myUserPrincipal.getPassword(),myUserPrincipal.getAuthorities());

        return user2;


/*        // 查询数据库操作
        if(!username.equals("admin")){
            throw new UsernameNotFoundException("the user is not found");
        }else{
            // 用户角色也应在数据库中获取
            String role = "ROLE_ADMIN";
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            // 线上环境应该通过用户名查询数据库获取加密后的密码
            String password = bcryptEncoder.encode("123456");
            // 返回默认的 User

            // 返回自定义的 KiteUserDetails
            User user = new User(username,password,authorities);
            return user;
        }*/
    }
}