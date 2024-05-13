package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.Role;
import com.hongha.ver1.entities.enums.ERole;
import com.hongha.ver1.reporitories.RoleReporitory;
import com.hongha.ver1.services.IRoleService;

@Service
public class RoleService implements IRoleService{
	@Autowired
	private RoleReporitory roleRepo;
	@Override
	public Role save(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public Role findById(long id) {
		return roleRepo.getReferenceById(id);
	}

	@Override
	public List<Role> getAll() {
		return roleRepo.findAll();
	}

	@Override
	public Role update(long id, Role roleRequest) {
		Role roleUpdate = roleRepo.getReferenceById(id);		
		roleUpdate.setName(roleRequest.getName());	
		return roleRepo.save(roleUpdate);
	}

	@Override
	public void delete(long id) {
		Role role = roleRepo.getReferenceById(id);
		if(role.getId()!=null){
			roleRepo.deleteById(id);
		}
	}

	@Override
	public Role findByUUID(UUID genId) {
		return roleRepo.findByUUID(genId);
	}

	@Override
	public Role updateByUUID(UUID genID, Role roleRequest) {
		Role roleUpdate = roleRepo.findByUUID(genID);		
		roleUpdate.setName(roleRequest.getName());		
		return roleRepo.save(roleUpdate);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		Role role = roleRepo.findByUUID(genID);
		if(role.getId()!=null){
			roleRepo.deleteById(role.getId());
		}
	}

	@Override
	public Role findByName(ERole name) {
		return roleRepo.findByName(name).get();
		 
	}
}
