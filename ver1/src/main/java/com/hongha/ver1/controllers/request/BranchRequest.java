package com.hongha.ver1.controllers.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hongha.ver1.dtos.BranchDTO;
import com.hongha.ver1.services.BranchService;

public class BranchRequest {
	@Autowired
	private BranchService branchService;
	public BranchDTO getBranch2Print(long id) {
		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setName("Chi nhánh 1");
		branchDTO.setNo(1);
		return branchDTO;
	} 
	public List<BranchDTO> getListBranch() {
		List<BranchDTO> list = new ArrayList();
		for (int i = 0; i < 5; i++) {
			BranchDTO branchDTO = new BranchDTO();
			branchDTO.setNo(i);
			branchDTO.setName("A"+i);
			list.add(branchDTO);
		}
		return list;

	}
}
