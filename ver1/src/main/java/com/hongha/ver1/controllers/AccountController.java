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

import com.hongha.ver1.dtos.AccountDTO;
import com.hongha.ver1.entities.Account;
import com.hongha.ver1.services.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AccountService accountService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(
			@RequestParam(required = false) String code,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "code") String sortBy,
			@RequestParam(defaultValue = "asc") String sortType) {
		try {
			Page<Account> accounts;
			if (code == null || code!="") {
				accounts = accountService.getAll(page, size, sortBy, sortType);
			} else {
				accounts = accountService.findByCodeContaining(code, page, size, sortBy, sortType);
			}
			// set No > DTO
			List<AccountDTO> accountDTOs = accounts.stream().map(account -> mapper.map(account, AccountDTO.class))
					.collect(Collectors.toList());
			for (int i = 0; i < accountDTOs.size(); i++) {
				accountDTOs.get(i).setNo(i + 1);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("accounts", accountDTOs);
			response.put("currentPage", accounts.getNumber());
			response.put("totalItems", accounts.getTotalElements());
			response.put("totalPages", accounts.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody AccountDTO accountDTO) {
		Account account = mapper.map(accountDTO, Account.class);
		Account accountCreated = accountService.save(account);
		AccountDTO result = mapper.map(accountCreated, AccountDTO.class);
		String msg = "";
		Map<String, Object> response = new HashMap<>();
		HttpStatus status;
		if (result != null) {
			msg = "create Success";
			status = HttpStatus.CREATED;
		} else {
			msg = "create Fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.put("account", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use uuid
	@GetMapping("/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		AccountDTO result = mapper.map(accountService.findByUUID(uuid), AccountDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getCode();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("account", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping("/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(
			@PathVariable("uuid") UUID uuid,
			@RequestBody AccountDTO accountDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, accountDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response,status);
		}
		Account account = mapper.map(accountDTO, Account.class);
		AccountDTO result = mapper.map(accountService.updateByUUID(uuid, account),AccountDTO.class);
		
		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getCode();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("account", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping("/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = accountService.deleteByUUID(uuid);
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
