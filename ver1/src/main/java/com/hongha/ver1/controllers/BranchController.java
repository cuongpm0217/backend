package com.hongha.ver1.controllers;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hongha.ver1.dtos.BranchDTO;
import com.hongha.ver1.entities.Branch;
import com.hongha.ver1.services.IBranchService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/branch")
public class BranchController {
	@Autowired
	private ModelMapper mapper;
//	@Autowired
//	private UserDetailsService userService;
	@Autowired
	private IBranchService branchService;

	public BranchController(IBranchService branchService) {
		super();
		this.branchService = branchService;
	}

	@GetMapping
	@ResponseBody
	public List<BranchDTO> getAll() {
		return branchService.getAll().stream().map(branch -> mapper.map(branch, BranchDTO.class))
				.collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public BranchDTO save(@RequestBody BranchDTO branchDTO) {
		Branch branch = mapper.map(branchDTO, Branch.class);
		Branch branchCreated = branchService.save(branch);
		return mapper.map(branchCreated, BranchDTO.class);
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public BranchDTO getOne(@PathVariable("id") long id) {
		return mapper.map(branchService.findById(id), BranchDTO.class);
	}

	@GetMapping(value = "/{uuid}")
	@ResponseBody
	public BranchDTO getOneByUUID(@PathVariable("uuid") UUID uuid) {
		return mapper.map(branchService.findByUUID(uuid), BranchDTO.class);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") long id, @RequestBody BranchDTO branchDTO) {
		if (!Objects.equals(id, branchDTO.getId())) {
			throw new IllegalArgumentException("IDs don't match");
		}
		Branch branch = mapper.map(branchDTO, Branch.class);
		branchService.update(id, branch);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") long id) {
		branchService.delete(id);
	}

	@PutMapping(value = "/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void updateByUUID(@PathVariable("uuid") UUID uuid, @RequestBody BranchDTO branchDTO) {
		if (!Objects.equals(uuid, branchDTO.getGenId())) {
			throw new IllegalArgumentException("UUIDs don't match");
		}
		Branch branch = mapper.map(branchDTO, Branch.class);
		branchService.updateByUUID(uuid, branch);
	}

	@DeleteMapping(value = "/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByUUID(@PathVariable("uuid") UUID uuid) {
		branchService.deleteByUUID(uuid);
	}

}
