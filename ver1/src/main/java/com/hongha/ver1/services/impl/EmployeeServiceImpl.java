package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Employee selected = empRepo.findByGenId(genId);
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
	public boolean delete(long id) {
		Employee selected = empRepo.getReferenceById(id);
		if (selected != null) {
			empRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Employee updateByUUID(UUID genID, Employee employeeRequest) {
		Employee selected = empRepo.findByGenId(genID);
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
	public boolean deleteByUUID(UUID genID) {
		Employee selected = empRepo.findByGenId(genID);
		if (selected != null) {
			empRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<Employee> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Employee> page = empRepo.findAll(pageable);
		return page;
	}

	@Override
	public Page<Employee> findByNameLike(String name, int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Employee> page = empRepo.findByNameLike(name, pageable);
		return page;
	}

	@Override
	public Page<Employee> findByPhone1OrPhone2Like(String phone1, String phone2, int pageNo, int pageSize,
			String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Employee> page = empRepo.findByPhone1OrPhone2Like(phone1, phone2, pageable);
		return page;
	}

	@Override
	public Page<Employee> findByBranchIdAndDepartmentId(long branchId, long departmentId, int pageNo, int pageSize,
			String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Employee> page = empRepo.findByBranchIdAndDepartmentId(branchId, departmentId, pageable);
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
