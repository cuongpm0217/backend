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

import com.hongha.ver1.dtos.CustomerDTO;
import com.hongha.ver1.entities.Customer;
import com.hongha.ver1.services.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "level") String sortBy,
			@RequestParam(defaultValue = "asc") String sortType) {
		try {
			Page<Customer> customers = customerService.getAll(page, size, sortBy, sortType);
			if (name != null || name != "") {
				customers = customerService.findByNameLike(name, page, size, sortBy, sortType);
			}
			if (phone != null || phone != "") {
				String phone1 = phone;
				String phone2 = phone;
				customers = customerService.findByPhone1OrPhone2Like(phone1, phone2, page, size, sortBy, sortType);
			}

			// set No > DTO
			List<CustomerDTO> customerDTOs = customers.stream().map(customer -> mapper.map(customer, CustomerDTO.class))
					.collect(Collectors.toList());
			for (int i = 0; i < customerDTOs.size(); i++) {
				CustomerDTO customerDTO = customerDTOs.get(i);
				customerDTO.setNo(i + 1);
				if (customerDTO.getGender() == null) {
					customerDTO.setTittle("Cty ");
				} else if (customerDTO.getGender()) {
					customerDTO.setTittle("Mr.");
				} else {
					customerDTO.setTittle("Ms.");
				}
				customerDTO.setName(customerDTO.getTittle() + customerDTO.getName());
			}

			Map<String, Object> response = new HashMap<>();
			response.put("customers", customerDTOs);
			response.put("currentPage", customers.getNumber());
			response.put("totalItems", customers.getTotalElements());
			response.put("totalPages", customers.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody CustomerDTO customerDTO) {
		Customer customer = mapper.map(customerDTO, Customer.class);
		Customer customerCreated = customerService.save(customer);
		CustomerDTO result = mapper.map(customerCreated, CustomerDTO.class);
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
		response.put("customer", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use UUID
	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		CustomerDTO result = mapper.map(customerService.findByUUID(uuid), CustomerDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			if (result.getGender() == null) {
				result.setTittle("Cty ");
			} else if (result.getGender()) {
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
		response.put("customer", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(value = "/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid,
			@RequestBody CustomerDTO customerDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, customerDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		Customer customer = mapper.map(customerDTO, Customer.class);
		CustomerDTO result = mapper.map(customerService.updateByUUID(uuid, customer), CustomerDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("customer", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(value = "/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = customerService.deleteByUUID(uuid);
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
