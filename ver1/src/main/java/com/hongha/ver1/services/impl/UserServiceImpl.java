package com.hongha.ver1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.CustomUserDetail;
import com.hongha.ver1.entities.User;
import com.hongha.ver1.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		else
			return new CustomUserDetail(user);
	}

	@Transactional
	public User updateProfile(User user) {
		User updated = userRepo.save(user);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update User:" + user.getId());
		}
	}

}
