package com.hongha.ver1.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String code,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "code") String sortBy, @RequestParam(defaultValue = "asc") String sortType)
			throws IOException {
		try {
			Page<Account> accounts;
			if (code == null) {
				accounts = accountService.getAll(page, size, sortBy, sortType);
			} else {
				accounts = accountService.findByCodeContaining(code, page, size, sortBy, sortType);
			}
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
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AccountDTO save(@RequestBody AccountDTO accountDTO) {
		Account account = mapper.map(accountDTO, Account.class);
		Account accountCreated = accountService.save(account);
		return mapper.map(accountCreated, AccountDTO.class);
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
	@ResponseBody
	public AccountDTO getOneByUUID(@PathVariable("uuid") UUID uuid) {
		return mapper.map(accountService.findByUUID(uuid), AccountDTO.class);
	}

	@PutMapping("/update-acc={uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void updateByUUID(@PathVariable("uuid") UUID uuid, @RequestBody AccountDTO accountDTO) {
		if (!Objects.equals(uuid, accountDTO.getGenId())) {
			throw new IllegalArgumentException("UUIDs don't match");
		}
		Account account = mapper.map(accountDTO, Account.class);
		accountService.updateByUUID(uuid, account);
	}

	@DeleteMapping("/delete-acc={uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByUUID(@PathVariable("uuid") UUID uuid) {
		accountService.deleteByUUID(uuid);
	}
}
