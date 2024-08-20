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

import com.hongha.ver1.entities.Branch;
import com.hongha.ver1.repositories.BranchRepository;
import com.hongha.ver1.services.BranchService;

@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	private BranchRepository branchRepo;

	@Override
	@Transactional
	public Branch save(Branch branch) {
		Branch isInserted = branchRepo.save(branch);
		if (branchRepo.getReferenceById(branch.getId()) != null) {
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
		} else {
			return null;
		}
	}

	@Override
	public List<Branch> getAll() {
		List<Branch> list = branchRepo.findAll();
		return list;

	}

	@Transactional
	@Override
	public Branch update(long id, Branch branchRequest) {
		Branch branchUpdate = branchRepo.getReferenceById(id);
		if (branchUpdate != null) {
			return updateObj(branchRequest, branchUpdate);
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(id));
		}
	}

	private Branch updateObj(Branch branchRequest, Branch branchUpdate) {
		branchUpdate.setAddress(branchRequest.getAddress());
		branchUpdate.setLevel(branchRequest.getLevel());
		branchUpdate.setName(branchRequest.getName());
		branchUpdate.setPhone1(branchRequest.getPhone1());
		branchUpdate.setPhone2(branchRequest.getPhone2());
		Branch updated = branchRepo.save(branchUpdate);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update");
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		Branch branch = branchRepo.getReferenceById(id);
		if (branch.getId() != null) {
			branchRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Branch findByUUID(UUID genId) {
		Branch branch = branchRepo.findByGenId(genId);
		if (branch != null) {
			return branch;
		} else {
			throw new RuntimeException("Not found");
		}
	}

	@Override
	@Transactional
	public Branch updateByUUID(UUID genID, Branch branchRequest) {
		Branch branchUpdate = branchRepo.findByGenId(genID);
		if (branchUpdate != null) {
			return updateObj(branchRequest, branchUpdate);
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		Branch branch = branchRepo.findByGenId(genID);
		if (branch != null) {
			branchRepo.deleteById(branch.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<Branch> findByNameContainingOrAddressContaining(String name, String address, int pageNo, int pageSize,
			String sortBy, String sortType) {
		Pageable paging = genPage(pageNo, pageSize, sortBy, sortType);
		Page<Branch> pageResult = branchRepo.findByNameContainingOrAddressContaining(name, address, paging);
		return pageResult;
	}

	@Override
	public Page<Branch> getAll(int pageNo, int pageSize, String sortBy, String sortType) {

		if (branchRepo.count() == 0) {
			loadDefaultBranch();
		}
		Pageable paging = genPage(pageNo, pageSize, sortBy, sortType);
		Page<Branch> pageResult = branchRepo.findAll(paging);

		return pageResult;
	}

	private Pageable genPage(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sorted = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sorted = sorted.descending();
		}
		Pageable paging = PageRequest.of(pageNo, pageSize, sorted);

		return paging;
	}

	private void loadDefaultBranch() {
		Branch branch = new Branch();
		branch.setAddress("Thị trấn Ngô Đồng, Giao Thủy, Nam Định");
		branch.setName("Chi nhánh Ngô Đồng");
		branch.setLevel(1);
		branch.setPhone1("0976625719");
		branchRepo.save(branch);
	}
}
