package com.csvw.oauth2auth.repository;

import com.csvw.oauth2auth.model.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepo extends JpaRepository<SysRole,Long>, JpaSpecificationExecutor<SysRole> {
}
