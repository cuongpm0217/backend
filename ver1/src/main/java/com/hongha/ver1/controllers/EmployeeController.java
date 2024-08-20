package com.hongha.ver1.controllers;

import java.util.ArrayList;
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
import org.springframework.security.core.userdetails.UserDetailsService;
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
import com.hongha.ver1.dtos.EmployeeDTO;
import com.hongha.ver1.dtos.PositionDTO;
import com.hongha.ver1.dtos.UserDTO;
import com.hongha.ver1.entities.Employee;
import com.hongha.ver1.services.DepartmentService;
import com.hongha.ver1.services.EmployeeService;
import com.hongha.ver1.services.PositionService;
import com.hongha.ver1.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "departmentId") String sortBy,
			@RequestParam(defaultValue = "asc") String sortType) {
		try {

			Page<Employee> employees = employeeService.getAll(page, size, sortBy, sortType);
			if (name != null)
				employees = employeeService.findByNameLike(name, page, size, sortBy, sortType);
			if (phone != null) {
				String phone1 = phone;
				String phone2 = phone;
				employees = employeeService.findByPhone1OrPhone2Like(phone1, phone2, page, size, sortBy, sortType);
			}

			List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
			for (Employee employee : employees) {
				PositionDTO positionDTO = mapper.map(positionService.findById(employee.getPositionId()),
						PositionDTO.class);
				DepartmentDTO departmentDTO = mapper.map(departmentService.findById(employee.getDepartmentId()),
						DepartmentDTO.class);
//				UserDTO userDTO = mapper.map(userService.findById(employee.getUserId()), UserDTO.class);
				EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
				employeeDTO.setDepartmentDTO(departmentDTO);
				employeeDTO.setPositionDTO(positionDTO);
//				employeeDTO.setUserDTO(userDTO);
				if (employeeDTO.isGender()) {
					employeeDTO.setTittle("Mr.");
				} else {
					employeeDTO.setTittle("Ms.");
				}

				employeeDTOs.add(employeeDTO);
			}
//			List<EmployeeDTO> employeeDTOs = employees.stream().map(employee -> mapper.map(employee, EmployeeDTO.class))
//					.collect(Collectors.toList());
			for (int i = 0; i < employeeDTOs.size(); i++) {
				EmployeeDTO employeeDTO = employeeDTOs.get(i);
				employeeDTO.setNo(i + 1);

			}

			Map<String, Object> response = new HashMap<>();
			response.put("employees", employeeDTOs);
			response.put("currentPage", employees.getNumber());
			response.put("totalItems", employees.getTotalElements());
			response.put("totalPages", employees.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody EmployeeDTO employeeDTO) {
		try {
			Employee employee = mapper.map(employeeDTO, Employee.class);
			employee.setDepartmentId(employeeDTO.getDepartmentDTO().getId());
			employee.setBranchId(employeeDTO.getDepartmentDTO().getBranchId());
			employee.setPositionId(employeeDTO.getPositionDTO().getId());
			Employee employeeCreated = employeeService.save(employee);
			EmployeeDTO result = mapper.map(employeeCreated, EmployeeDTO.class);

			Map<String, Object> response = new HashMap<>();
			String msg = "";
			HttpStatus status;
			if (result != null) {
				result.setDepartmentDTO(employeeDTO.getDepartmentDTO());
				result.setPositionDTO(employeeDTO.getPositionDTO());
				msg = "create Success";
				status = HttpStatus.CREATED;
			} else {
				msg = "create Failse";
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			response.put("employee", employeeCreated);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// use UUID
	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable UUID uuid) {
		EmployeeDTO result = mapper.map(employeeService.findByUUID(uuid), EmployeeDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			if (result.isGender()) {
				result.setTittle("Mr.");
			} else {
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
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable UUID uuid,
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
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable UUID uuid) {
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
