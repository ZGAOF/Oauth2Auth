package com.csvw.oauth2auth.repository;

import com.csvw.oauth2auth.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor<SysUser> {

    SysUser findByuserId(Long id);
    /*@Query(nativeQuery = true,
            value =
                    "select u.user_id, u.username, u.password, r.role, p.authority " +
                            "from sys_user as u join sys_role as r join sys_permission as p " +
                            "on u.user_id = r.role_id and r.role_id = p.permission_id;")
    SysUser findByUsername(String username);*/

   SysUser findByUsername(String username);


}
