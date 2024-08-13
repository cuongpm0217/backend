package com.hongha.ver1.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hongha.ver1.dtos.BranchDTO;
import com.hongha.ver1.entities.Branch;
import com.hongha.ver1.services.BranchService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping("/auth/branch")
public class BranchController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BranchService branchService;

	@GetMapping("/auth/branch/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) String address, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy,
			@RequestParam(defaultValue = "asc") String sortType) {
		try {
			page = page - 1;// front end always start 1
			Page<Branch> branches = branchService.getAll(page, size, sortBy, sortType);
			if ((name != null || name == "") || (address != null || address == "")) {
				branches = branchService.findByNameContainingOrAddressContaining(name, address, page, size, sortBy, sortType);
			}					
			// set No > DTO
			List<BranchDTO> branchDTOs = branches.stream().map(branch -> mapper.map(branch, BranchDTO.class))
					.collect(Collectors.toList());
			for (int i = 0; i < branchDTOs.size(); i++) {
				branchDTOs.get(i).setNo(i + 1);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("branchs", branchDTOs);
			response.put("currentPage", branches.getNumber());
			response.put("totalItems", branches.getTotalElements());
			response.put("totalPages", branches.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/api/branch/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody BranchDTO branchDTO) {
		Branch branch = mapper.map(branchDTO, Branch.class);
		Branch branchCreated = branchService.save(branch);
		BranchDTO result= mapper.map(branchCreated, BranchDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			msg = "create Success";
			status = HttpStatus.CREATED;
		} else {
			msg = "create Fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.put("branch", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use UUID
	@GetMapping(value = "/api/branch/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		BranchDTO result =  mapper.map(branchService.findByUUID(uuid), BranchDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("branch", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(value = "/api/branch/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid, @RequestBody BranchDTO branchDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, branchDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response,status);
		}
		Branch branch = mapper.map(branchDTO, Branch.class);
		BranchDTO result = mapper.map(branchService.updateByUUID(uuid, branch),BranchDTO.class);
		
		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("branch", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(value = "/api/branch/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = branchService.deleteByUUID(uuid);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result) {
			status = HttpStatus.OK;
			msg = "DELETED";
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("result", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

}
