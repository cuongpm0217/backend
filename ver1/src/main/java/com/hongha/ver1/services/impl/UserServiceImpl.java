package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.CustomUserDetail;
import com.hongha.ver1.entities.Employee;
import com.hongha.ver1.entities.User;
import com.hongha.ver1.repositories.UserRepository;
import com.hongha.ver1.services.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
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

	@Override
	public User save(User userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUUID(UUID genId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(long id, User userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User updateByUUID(UUID genID, User userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteByUUID(UUID genID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page<User> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<User> page = userRepo.findAll(pageable);
		return page;
	}
	private Pageable genPageable(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return pageable;
	}

}
