package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

<<<<<<< HEAD
import com.hongha.ver1.entities.Role;
import com.hongha.ver1.entities.enums.ERole;
=======
import com.hongha.ver1.entities.ERole;
import com.hongha.ver1.entities.Role;
>>>>>>> a833e23cca83a037a87c92ee39e57e07f3c32774

public interface IRoleService {
	Role save(Role RoleRequest);
	Role findById(long id);
	Role findByUUID(UUID genId);
	Role findByName(ERole name);
	List<Role> getAll();
	Role update(long id,Role RoleRequest);
	void delete(long id);
	Role updateByUUID(UUID genID,Role RoleRequest);
	void deleteByUUID(UUID genID);
	
}
