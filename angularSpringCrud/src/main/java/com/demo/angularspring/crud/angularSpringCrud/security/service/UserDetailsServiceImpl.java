package com.demo.angularspring.crud.angularSpringCrud.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.angularspring.crud.angularSpringCrud.entity.User;
import com.demo.angularspring.crud.angularSpringCrud.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByuserName(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email :"  + username));
				
		return UserPrinciple.build(user);
	}

}
