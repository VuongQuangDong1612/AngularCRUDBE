package com.demo.angularspring.crud.angularSpringCrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.angularspring.crud.angularSpringCrud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByuserName(String username);
     Boolean existsByuserName(String username);
     Boolean existsByEmail(String email);
}
