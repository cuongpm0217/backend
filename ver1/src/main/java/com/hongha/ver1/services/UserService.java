package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.User;

public interface UserService {
	User save(User userRequest);

	User findById(long id);

	User findByUUID(UUID genId);

	List<User> getAll() ;

	User update(long id, User userRequest);

	boolean delete(long id);

	User updateByUUID(UUID genID, User userRequest);

	boolean deleteByUUID(UUID genID);	

	Page<User> getAll(int pageNo, int pageSize, String sortBy, String sortType);

}
