package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Department;
import com.hongha.ver1.repositories.DepartmentRepository;
import com.hongha.ver1.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository depRepo;

	@Override
	@Transactional
	public Department save(Department departmentRequest) {
		Department isInserted = depRepo.save(departmentRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Department");
		}

	}

	@Override
	public Department findById(long id) {
		Department selected = depRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(id));
		}
	}

	@Override
	public Department findByUUID(UUID genId) {
		Department selected = depRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Department> getAll() {
		return depRepo.findAll();
	}

	@Override
	@Transactional
	public Department update(long id, Department departmentRequest) {
		Department selected = depRepo.getReferenceById(id);
		if (selected != null) {
			selected.setName(departmentRequest.getName());
			Department updated = depRepo.save(selected);
			if(updated!=null) {
				return updated;
			}else {
				throw new RuntimeException("Can't update Department:"+String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(id));
		}		
	}

	@Override
	@Transactional
	public void delete(long id) {
		Department selected = depRepo.getReferenceById(id);
		if(selected!=null) {
			depRepo.deleteById(id);
		}else {
			throw new RuntimeException("Not found Department:"+String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Department updateByUUID(UUID genID, Department departmentRequest) {
		Department selected = depRepo.findByUUID(genID);
		if (selected != null) {
			selected.setName(departmentRequest.getName());
			Department updated = depRepo.save(selected);
			if(updated!=null) {
				return updated;
			}else {
				throw new RuntimeException("Can't update Department:"+String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Department selected = depRepo.findByUUID(genID);
		if(selected!=null) {
			depRepo.deleteById(selected.getId());
		}else {
			throw new RuntimeException("Not found Department:"+String.valueOf(genID));
		}
	}

}
