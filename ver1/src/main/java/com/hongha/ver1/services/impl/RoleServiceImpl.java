package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Role;
import com.hongha.ver1.entities.enums.ERole;
import com.hongha.ver1.repositories.RoleRepository;
import com.hongha.ver1.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepo;

	@Override
	@Transactional
	public Role save(Role role) {
		Role isInserted = roleRepo.save(role);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Role");
		}
	}

	@Override
	public Role findById(long id) {
		Role selected = roleRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Role:" + String.valueOf(id));
		}
	}

	@Override
	public List<Role> getAll() {
		return roleRepo.findAll();
	}

	@Override
	@Transactional
	public Role update(long id, Role roleRequest) {
		Role roleUpdate = roleRepo.getReferenceById(id);
		if (roleUpdate != null) {
			roleUpdate.setName(roleRequest.getName());
			Role updated = roleRepo.save(roleUpdate);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Role:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Role:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Role role = roleRepo.getReferenceById(id);
		if (role.getId() != null) {
			roleRepo.deleteById(id);
		}
	}

	@Override
	public Role findByUUID(UUID genId) {
		Role selected = roleRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Role:" + String.valueOf(genId));
		}
	}

	@Override
	@Transactional
	public Role updateByUUID(UUID genID, Role roleRequest) {
		Role roleUpdate = roleRepo.findByGenId(genID);
		if (roleUpdate != null) {
			roleUpdate.setName(roleRequest.getName());
			Role updated = roleRepo.save(roleUpdate);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Role:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Role:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Role role = roleRepo.findByGenId(genID);
		if (role != null) {
			roleRepo.deleteById(role.getId());
		} else {
			throw new RuntimeException("Not found Role:" + String.valueOf(genID));
		}
	}

	@Override
	public Role findByName(ERole name) {
		Role selected = roleRepo.findByName(name).get();
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Role:" + name.toString());
		}
	}
}
