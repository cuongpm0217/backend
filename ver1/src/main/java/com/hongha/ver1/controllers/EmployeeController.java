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

import com.hongha.ver1.dtos.EmployeeDTO;
import com.hongha.ver1.entities.Employee;
import com.hongha.ver1.services.EmployeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "level") String sortBy,
			@RequestParam(defaultValue = "asc") String sortType) {
		try {
			Page<Employee> employees;
			if (name != null || name != "")
				employees = employeeService.findByNameLike(name, page, size, sortBy, sortType);
			if (phone != "" || phone != null) {
				String phone1 = phone;
				String phone2 = phone;
				employees = employeeService.findByPhone1OrPhone2Like(phone1, phone2, page, size, sortBy, sortType);
			} else
				employees = employeeService.getAll(page, size, sortBy, sortType);
			// set No > DTO
			List<EmployeeDTO> employeeDTOs = employees.stream().map(employee -> mapper.map(employee, EmployeeDTO.class))
					.collect(Collectors.toList());
			for (int i = 0; i < employeeDTOs.size(); i++) {
				EmployeeDTO item =employeeDTOs.get(i);
				item.setNo(i + 1);
				if(item.isGender()) {
					item.setTittle("Mr.");
				}else {
					item.setTittle("Ms.");
				}
				item.setName(item.getTittle()+item.getName());
			}

			Map<String, Object> response = new HashMap<>();
			response.put("employees", employeeDTOs);
			response.put("currentPage", employees.getNumber());
			response.put("totalItems", employees.getTotalElements());
			response.put("totalPages", employees.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = mapper.map(employeeDTO, Employee.class);
		Employee employeeCreated = employeeService.save(employee);
		EmployeeDTO result = mapper.map(employeeCreated, EmployeeDTO.class);
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
		response.put("employee", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use UUID
	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		EmployeeDTO result = mapper.map(employeeService.findByUUID(uuid), EmployeeDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			if(result.isGender()) {
				result.setTittle("Mr.");
			}else {
				result.setTittle("Ms.");
			}
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("employee", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(value = "/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid,
			@RequestBody EmployeeDTO employeeDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, employeeDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		Employee employee = mapper.map(employeeDTO, Employee.class);
		EmployeeDTO result = mapper.map(employeeService.updateByUUID(uuid, employee), EmployeeDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("employee", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(value = "/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = employeeService.deleteByUUID(uuid);
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
