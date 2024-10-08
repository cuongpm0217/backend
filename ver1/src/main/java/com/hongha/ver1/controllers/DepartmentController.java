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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hongha.ver1.dtos.DepartmentDTO;
import com.hongha.ver1.entities.Branch;
import com.hongha.ver1.entities.Department;
import com.hongha.ver1.services.BranchService;
import com.hongha.ver1.services.DepartmentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private BranchService branchService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String query,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "vname") String sortBy, @RequestParam(defaultValue = "asc") String sortType) {
		try {
			page = page - 1;
			Page<Department> departments = departmentService.getAll(page, size, sortBy, sortType);
			if (query != null || query == "") {
				departments = departmentService.findBySearchText(query, page, size, sortBy, sortType);
			}
			// set No > DTO
			List<DepartmentDTO> departmentDTOs = departments.stream()
					.map(department -> mapper.map(department, DepartmentDTO.class)).collect(Collectors.toList());
			for (int i = 0; i < departmentDTOs.size(); i++) {
				departmentDTOs.get(i).setNo(i + 1 + (page * size));
				String branchName = "";
				if (departmentDTOs.get(i).getBranchId() != 0) {
					Branch b = branchService.findById(departmentDTOs.get(i).getBranchId());
					if (b != null) {
						branchName = b.getName();
					}
				}
				departmentDTOs.get(i).setBranchName(branchName);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("departments", departmentDTOs);
			response.put("currentPage", departments.getNumber());
			response.put("totalItems", departments.getTotalElements());
			response.put("totalPages", departments.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody DepartmentDTO departmentDTO) {
		Department department = mapper.map(departmentDTO, Department.class);
		Department departmentCreated = departmentService.save(department);
		DepartmentDTO result = mapper.map(departmentCreated, DepartmentDTO.class);
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
		response.put("department", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use UUID
	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		DepartmentDTO result = mapper.map(departmentService.findByUUID(uuid), DepartmentDTO.class);

		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			String VName = "";
			if (result.getBranchId() != 0) {
				Branch b = branchService.findById(result.getBranchId());
				if (b != null) {
					VName = b.getName();
				}
			}
			result.setBranchName(VName);
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("department", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(value = "/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid,
			@RequestBody DepartmentDTO departmentDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, departmentDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		Department department = mapper.map(departmentDTO, Department.class);
		DepartmentDTO result = mapper.map(departmentService.updateByUUID(uuid, department), DepartmentDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("department", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(value = "/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = departmentService.deleteByUUID(uuid);
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
