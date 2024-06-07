package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Branch;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.repositories.BranchRepository;
import com.hongha.ver1.repositories.HistoryRepository;
import com.hongha.ver1.services.BranchService;

@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	private BranchRepository branchRepo;
	@Autowired
	private HistoryRepository historyRepo;
	private History history;

	@Override
	public Branch save(Branch branch) {
		Branch isInserted = branchRepo.save(branch);
		if (branchRepo.getReferenceById(branch.getId()) != null) {
			history = new History(Branch.class.getName(), EAction.CREATE, branch.getId().toString());
			historyRepo.save(history);
			return isInserted;
		} else {
			throw new RuntimeException("Can't save branch " + branch.getId());
		}
	}

	@Override
	public Branch findById(long id) {
		Branch branch = branchRepo.getReferenceById(id);
		if (branch != null) {
			return branch;
		} else
			throw new RuntimeException("Not found");
	}

	@Override
	public List<Branch> getAll() {
		List<Branch> list = branchRepo.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new RuntimeException("Branch is empty");
		}

	}

	@Transactional
	@Override
	public Branch update(long id, Branch branchRequest) {
		Branch branchUpdate = branchRepo.getReferenceById(id);
		branchUpdate.setAddress(branchRequest.getAddress());
		branchUpdate.setLevel(branchRequest.getLevel());
		branchUpdate.setName(branchRequest.getName());
		branchUpdate.setPhone1(branchRequest.getPhone1());
		branchUpdate.setPhone2(branchRequest.getPhone2());
		history = new History(Branch.class.getName(), EAction.UPDATE, branchUpdate.getId().toString());
		historyRepo.save(history);
		return branchRepo.save(branchUpdate);
	}

	@Transactional
	@Override
	public void delete(long id) {
		Branch branch = branchRepo.getReferenceById(id);
		if (branch.getId() != null) {
			history = new History(Branch.class.getName(), EAction.DELETE, branch.getId().toString());
			historyRepo.save(history);
			branchRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found");
		}
	}

	@Override
	public Branch findByUUID(UUID genId) {
		Branch branch = branchRepo.findByUUID(genId);
		if (branch != null) {
			return branch;
		} else
			throw new RuntimeException("Not found");

	}

	@Transactional
	@Override
	public Branch updateByUUID(UUID genID, Branch branchRequest) {
		Branch branchUpdate = branchRepo.findByUUID(genID);
		branchUpdate.setAddress(branchRequest.getAddress());
		branchUpdate.setLevel(branchRequest.getLevel());
		branchUpdate.setName(branchRequest.getName());
		branchUpdate.setPhone1(branchRequest.getPhone1());
		branchUpdate.setPhone2(branchRequest.getPhone2());
		history = new History(Branch.class.getName(), EAction.UPDATE, branchUpdate.getGenId().toString());
		historyRepo.save(history);
		return branchRepo.save(branchUpdate);
	}

	@Transactional
	@Override
	public void deleteByUUID(UUID genID) {
		Branch branch = branchRepo.findByUUID(genID);
		if (branch != null) {
			history = new History(Branch.class.getName(), EAction.DELETE, branch.getGenId().toString());
			historyRepo.save(history);
			branchRepo.deleteById(branch.getId());
		} else
			throw new RuntimeException("Not found");
	}

}
