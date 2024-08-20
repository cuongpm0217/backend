package com.hongha.ver1.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.hongha.ver1.dtos.CustomerTypeDTO;
import com.hongha.ver1.entities.CustomerType;
import com.hongha.ver1.services.CustomerTypeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer-type")
public class CustomerTypeController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CustomerTypeService customerTypeService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll() {
		try {
			List<CustomerType> customerTypes = customerTypeService.getAll();
			// set No > DTO
			List<CustomerTypeDTO> customerTypeDTOs = customerTypes.stream()
					.map(customerType -> mapper.map(customerType, CustomerTypeDTO.class)).collect(Collectors.toList());
			for (int i = 0; i < customerTypeDTOs.size(); i++) {
				customerTypeDTOs.get(i).setNo(i + 1);
			}
			Map<String, Object> response = new HashMap<>();
			response.put("customerTypes", customerTypeDTOs);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody CustomerTypeDTO customerTypeDTO) {
		CustomerType customerType = mapper.map(customerTypeDTO, CustomerType.class);
		CustomerType customerTypeCreated = customerTypeService.save(customerType);
		CustomerTypeDTO result = mapper.map(customerTypeCreated, CustomerTypeDTO.class);
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
		response.put("customerType", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use UUID
	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable UUID uuid) {
		CustomerTypeDTO result = mapper.map(customerTypeService.findByUUID(uuid), CustomerTypeDTO.class);
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
		response.put("customerType", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(value = "/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable UUID uuid,
			@RequestBody CustomerTypeDTO customerTypeDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, customerTypeDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		CustomerType customerType = mapper.map(customerTypeDTO, CustomerType.class);
		CustomerTypeDTO result = mapper.map(customerTypeService.updateByUUID(uuid, customerType),
				CustomerTypeDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("customerType", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(value = "/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable UUID uuid) {
		boolean result = customerTypeService.deleteByUUID(uuid);
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
