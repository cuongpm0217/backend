package com.hongha.ver1.services;


import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Branch;

public interface IBranchService {
	Branch save(Branch branchRequest);
	Branch findById(long id);
	Branch findByUUID(UUID genId);
	List<Branch> getAll();
	Branch update(long id,Branch branchRequest);
	void delete(long id);
	Branch updateByUUID(UUID genID,Branch branchRequest);
	void deleteByUUID(UUID genID);
	
}
