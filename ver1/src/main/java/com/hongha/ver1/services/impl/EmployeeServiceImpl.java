package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Employee;
import com.hongha.ver1.repositories.EmployeeRepository;
import com.hongha.ver1.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	@Transactional
	public Employee save(Employee employeeRequest) {
		Employee isInserted = empRepo.save(employeeRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Employee");
		}

	}

	@Override
	public Employee findById(long id) {
		Employee selected = empRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Employee:" + String.valueOf(id));
		}
	}

	@Override
	public Employee findByUUID(UUID genId) {
		Employee selected = empRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Employee:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	@Override
	@Transactional
	public Employee update(long id, Employee employeeRequest) {
		Employee selected = empRepo.getReferenceById(id);
		if (selected != null) {
			selected.setAddress1(employeeRequest.getAddress1());
			selected.setAddress2(employeeRequest.getAddress2());
			selected.setAvatar(employeeRequest.getAvatar());
			selected.setBranchId(employeeRequest.getBranchId());
			selected.setDepartmentId(employeeRequest.getDepartmentId());
			selected.setDob(employeeRequest.getDob());
			selected.setName(employeeRequest.getName());
			selected.setNationalID(employeeRequest.getNationalID());
			selected.setPhone1(employeeRequest.getPhone1());
			selected.setPhone2(employeeRequest.getPhone2());
			selected.setPositionId(employeeRequest.getPositionId());

			Employee updated = empRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Employee:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Employee:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Employee selected = empRepo.getReferenceById(id);
		if (selected != null) {
			empRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Employee:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Employee updateByUUID(UUID genID, Employee employeeRequest) {
		Employee selected = empRepo.findByUUID(genID);
		if (selected != null) {
			selected.setAddress1(employeeRequest.getAddress1());
			selected.setAddress2(employeeRequest.getAddress2());
			selected.setAvatar(employeeRequest.getAvatar());
			selected.setBranchId(employeeRequest.getBranchId());
			selected.setDepartmentId(employeeRequest.getDepartmentId());
			selected.setDob(employeeRequest.getDob());
			selected.setName(employeeRequest.getName());
			selected.setNationalID(employeeRequest.getNationalID());
			selected.setPhone1(employeeRequest.getPhone1());
			selected.setPhone2(employeeRequest.getPhone2());
			selected.setPositionId(employeeRequest.getPositionId());
			Employee updated = empRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Employee:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Employee:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Employee selected = empRepo.findByUUID(genID);
		if (selected != null) {
			empRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Employee:" + String.valueOf(genID));
		}
	}
}
