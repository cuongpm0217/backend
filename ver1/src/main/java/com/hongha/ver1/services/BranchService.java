package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Branch;

public interface BranchService {
	Branch save(Branch branchRequest);

	Branch findById(long id);

	Branch findByUUID(UUID genId);

	List<Branch> getAll();

	Branch update(long id, Branch branchRequest);

	boolean delete(long id);

	Branch updateByUUID(UUID genID, Branch branchRequest);

	boolean deleteByUUID(UUID genID);

	Page<Branch> findByNameOrAddressLike(String name, String address, int pageNo, int pageSize, String sortBy,
			String sortType);

	Page<Branch> getAll(int pageNo, int pageSize, String sortBy, String sortType);
}
