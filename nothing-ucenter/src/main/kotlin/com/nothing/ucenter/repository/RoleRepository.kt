package com.nothing.ucenter.repository

import com.nothing.ucenter.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface RoleRepository : JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

}