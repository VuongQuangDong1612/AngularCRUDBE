package com.demo.angularspring.crud.angularSpringCrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.angularspring.crud.angularSpringCrud.entity.User;
import com.demo.angularspring.crud.angularSpringCrud.security.jwt.JwtProvider;
import com.demo.angularspring.crud.angularSpringCrud.security.jwt.JwtResponse;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody User userLogin) {
		
		Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.genarateJwtToken(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
}
