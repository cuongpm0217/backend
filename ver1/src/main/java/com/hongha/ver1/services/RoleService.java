package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Role;
import com.hongha.ver1.entities.enums.ERole;

public interface RoleService {
	Role save(Role RoleRequest);

	Role findById(long id);

	Role findByUUID(UUID genId);

	Role findByName(ERole name);

	List<Role> getAll();

	Role update(long id, Role RoleRequest);

	boolean delete(long id);

	Role updateByUUID(UUID genID, Role RoleRequest);

	boolean deleteByUUID(UUID genID);

}
