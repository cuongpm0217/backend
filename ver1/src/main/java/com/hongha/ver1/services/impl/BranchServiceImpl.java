package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.Branch;
import com.hongha.ver1.repositories.BranchRepository;
import com.hongha.ver1.services.BranchService;
@Service
public class BranchServiceImpl implements BranchService{
	@Autowired
	private BranchRepository branchRepo;
	@Override
	public Branch save(Branch branch) {
		return branchRepo.save(branch);
	}

	@Override
	public Branch findById(long id) {
		return branchRepo.getReferenceById(id);
	}

	@Override
	public List<Branch> getAll() {
		return branchRepo.findAll();
	}

	@Override
	public Branch update(long id, Branch branchRequest) {
		Branch branchUpdate = branchRepo.getReferenceById(id);
		branchUpdate.setAddress(branchRequest.getAddress());
		branchUpdate.setLevel(branchRequest.getLevel());
		branchUpdate.setName(branchRequest.getName());
		branchUpdate.setPhone1(branchRequest.getPhone1());
		branchUpdate.setPhone2(branchRequest.getPhone2());
	
		return branchRepo.save(branchUpdate);
	}

	@Override
	public void delete(long id) {
		Branch branch = branchRepo.getReferenceById(id);
		if(branch.getId()!=null){
			branchRepo.deleteById(id);
		}
	}

	@Override
	public Branch findByUUID(UUID genId) {
		return branchRepo.findByUUID(genId);
	}

	@Override
	public Branch updateByUUID(UUID genID, Branch branchRequest) {
		Branch branchUpdate = branchRepo.findByUUID(genID);
		branchUpdate.setAddress(branchRequest.getAddress());
		branchUpdate.setLevel(branchRequest.getLevel());
		branchUpdate.setName(branchRequest.getName());
		branchUpdate.setPhone1(branchRequest.getPhone1());
		branchUpdate.setPhone2(branchRequest.getPhone2());	
		return branchRepo.save(branchUpdate);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		Branch branch = branchRepo.findByUUID(genID);
		if(branch.getId()!=null){
			branchRepo.deleteById(branch.getId());
		}
	}

}
