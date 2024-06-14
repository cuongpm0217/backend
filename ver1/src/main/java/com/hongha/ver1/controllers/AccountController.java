package com.hongha.ver1.controllers;

import java.io.IOException;

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
	@ResponseBody
	public List<AccountDTO> getAll() throws IOException {
		List<Account> accounts = accountService.getAll();
		return accounts.stream().map(account -> mapper.map(account, AccountDTO.class)).collect(Collectors.toList());
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
