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

import com.hongha.ver1.controllers.response.Pagination;
import com.hongha.ver1.dtos.AccountDTO;
import com.hongha.ver1.entities.Account;
import com.hongha.ver1.redisService.AccountRedisService;
import com.hongha.ver1.services.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRedisService accountRedisService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String query,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "code") String sortBy, @RequestParam(defaultValue = "asc") String sortType) {

		try {
			page = page - 1;// front end always start 1		
			query = query != null ? query : "";

			List<AccountDTO> accountDTOs = accountRedisService.findBySearchText(query, page, size, sortBy, sortType);
			Pagination pagination = accountRedisService.getPagination(query);
			if (accountDTOs == null || pagination == null) {
				Page<Account> accounts = accountService.findBySearchText(query, page, size, sortBy, sortType);
				if (accountDTOs == null) {
					accountDTOs = accounts.stream().map(account -> mapper.map(account, AccountDTO.class))
							.collect(Collectors.toList());
					accountRedisService.saveAllAccount(accountDTOs, query, page, size, sortBy, sortType);
				} 
				if (pagination == null) {
					pagination = Pagination.builder().currentPage(accounts.getNumber())
							.totalItem(accounts.getTotalElements()).totalPage(accounts.getTotalPages()).build();
					accountRedisService.savePagination(pagination, query);
				}

			} 

			// set No > DTO
			for (int i = 0; i < accountDTOs.size(); i++) {
				accountDTOs.get(i).setNo(i + 1 + (page * size));
				switch (accountDTOs.get(i).getLevel()) {
				case 1: {
					accountDTOs.get(i).setStyle("level1");
					break;
				}
				case 2: {
					accountDTOs.get(i).setStyle("level2");
					break;
				}
				case 3: {
					accountDTOs.get(i).setStyle("level3");
					break;
				}
				}

			}
			Map<String, Object> response = new HashMap<>();
			response.put("accounts", accountDTOs);
			response.put("currentPage", pagination.getCurrentPage());
			response.put("totalItems", pagination.getTotalItem());
			response.put("totalPages", pagination.getCurrentPage());
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
			accountRedisService.clearList();
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
		try {
			AccountDTO result = accountRedisService.getOne(uuid);
			if (result == null) {
				result = mapper.map(accountService.findByUUID(uuid), AccountDTO.class);
				accountRedisService.saveAccount(result);
			}
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
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid,
			@RequestBody AccountDTO accountDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, accountDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		Account account = mapper.map(accountDTO, Account.class);
		AccountDTO result = mapper.map(accountService.updateByUUID(uuid, account), AccountDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getCode();
			accountRedisService.clearOne(uuid);
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
			accountRedisService.clearOne(uuid);
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("result", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}
}
