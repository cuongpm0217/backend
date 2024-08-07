package com.hongha.ver1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hongha.ver1.entities.CustomUserDetail;

import com.hongha.ver1.entities.Role;
import com.hongha.ver1.entities.User;
import com.hongha.ver1.entities.enums.ERole;
import com.hongha.ver1.repositories.RoleRepository;
import com.hongha.ver1.repositories.UserRepository;
import com.hongha.ver1.securties.jwt.JwtUtils;
import com.hongha.ver1.securties.jwt.payload.request.LoginRequest;
import com.hongha.ver1.securties.jwt.payload.request.SignupRequest;
import com.hongha.ver1.securties.jwt.payload.response.JwtResponse;
import com.hongha.ver1.securties.jwt.payload.response.MessageResponse;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired()
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired()
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtUtils provider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
			String jwt = provider.generateToken(userDetail);

			List<String> roles = userDetail.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt, userDetail.getGenId(), userDetail.getUsername(),
					userDetail.getEmail(), roles,userDetail.getBranchId()));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Đăng nhập không thành công"));
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()),signUpRequest.getBranchId());

		Set<String> strRoles;
		strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		// check role in DB
		if (roleRepository.count()==0) {
			loadDefaultRole();
		}
		//check role in FE
		if (strRoles == null || strRoles.isEmpty()) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "MANAGER":
					Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		user.setCreatedBy(user.getUsername());
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	private void loadDefaultRole() {
		Role roleAdmin = new Role();
		roleAdmin.setName(ERole.ROLE_ADMIN);
		roleAdmin.setCreatedAt(new Date());
		roleAdmin.setCreatedBy("admin");
		Role roleManager = new Role();
		roleManager.setName(ERole.ROLE_MANAGER);
		roleManager.setCreatedAt(new Date());
		roleManager.setCreatedBy("admin");
		Role roleUser = new Role();
		roleUser.setName(ERole.ROLE_USER);
		roleUser.setCreatedAt(new Date());
		roleUser.setCreatedBy("admin");
		roleRepository.save(roleAdmin);
		roleRepository.save(roleManager);
		roleRepository.save(roleUser);
	}
	
}
