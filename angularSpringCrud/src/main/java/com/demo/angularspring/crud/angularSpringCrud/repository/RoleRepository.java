package com.demo.angularspring.crud.angularSpringCrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.angularspring.crud.angularSpringCrud.entity.Role;
import com.demo.angularspring.crud.angularSpringCrud.entity.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByroleName(RoleName roleName);
}
