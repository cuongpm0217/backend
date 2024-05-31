package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.Branch;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.Role;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.entities.enums.ERole;
import com.hongha.ver1.repositories.HistoryRepository;
import com.hongha.ver1.repositories.RoleRepository;
import com.hongha.ver1.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private HistoryRepository historyRepo;
	private History history;
	
	@Override
	public Role save(Role role) {
		history = new History(Role.class.getName(),EAction.CREATE,role.getId().toString());
		historyRepo.save(history);
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
		history = new History(Role.class.getName(),EAction.UPDATE,roleRequest.getId().toString());
		historyRepo.save(history);
		return roleRepo.save(roleUpdate);
	}

	@Override
	public void delete(long id) {
		Role role = roleRepo.getReferenceById(id);
		if (role.getId() != null) {
			history = new History(Role.class.getName(),EAction.DELETE,String.valueOf(id));
			historyRepo.save(history);
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
		history = new History(Role.class.getName(),EAction.UPDATE,roleRequest.getGenId().toString());
		historyRepo.save(history);
		return roleRepo.save(roleUpdate);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		Role role = roleRepo.findByUUID(genID);
		if (role.getId() != null) {
			history = new History(Role.class.getName(),EAction.DELETE,role.getGenId().toString());
			historyRepo.save(history);
			roleRepo.deleteById(role.getId());
		}
	}

	@Override
	public Role findByName(ERole name) {
		return roleRepo.findByName(name).get();

	}
}
