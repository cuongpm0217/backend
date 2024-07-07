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
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String code,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "code") String sortBy, @RequestParam(defaultValue = "asc") String sortType) {
		try {
			Page<Account> accounts;
			if (code == null) {
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
			msg = "Success";
			status = HttpStatus.CREATED;
		} else {
			msg = "Fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.put("account", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use id
	/*
	 * @GetMapping("/id={id}")
	 * 
	 * @ResponseBody public AccountDTO getOne(@PathVariable("id") long id) { Account
	 * acc = accountService.findById(id); AccountDTO accDTO = mapper.map(acc,
	 * AccountDTO.class); return accDTO; }
	 * 
	 * @PutMapping("/update-id={id}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public void update(@PathVariable("id") long
	 * id, @RequestBody AccountDTO accountDTO) { if (!Objects.equals(id,
	 * accountDTO.getId())) { throw new IllegalArgumentException("IDs don't match");
	 * } Account account = mapper.map(accountDTO, Account.class);
	 * accountService.update(id, account); }
	 * 
	 * @DeleteMapping("/delete-id={id}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public void delete(@PathVariable("id") long
	 * id) { accountService.delete(id); }
	 */
	// use uuid
	@GetMapping("/uuid={uuid}")
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

	@PutMapping("/update-account={uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid, @RequestBody AccountDTO accountDTO) {
		if (!Objects.equals(uuid, accountDTO.getGenId())) {
			throw new IllegalArgumentException("UUIDs don't match");
		}
		Account account = mapper.map(accountDTO, Account.class);
		Account result =  accountService.updateByUUID(uuid, account);
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

	@DeleteMapping("/delete-account={uuid}")
	@ResponseStatus(HttpStatus.OK)
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
