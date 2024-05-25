package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.Account;
import com.hongha.ver1.repositories.AccountRepository;
import com.hongha.ver1.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepo;
	
	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return username;
	}
	
	@Override
	public Account save(Account accountRequest) {
		accountRequest.setCreatedBy(getUserName());
		accountRequest.setUpdatedBy(getUserName());
		return accountRepo.save(accountRequest);
	}

	@Override
	public Account findById(long id) {
		return accountRepo.getReferenceById(id);
	}

	@Override
	public Account findByUUID(UUID genId) {
		return accountRepo.findByUUID(genId);
	}

	@Override
	public List<Account> getAll() {

		return accountRepo.findAll();
	}

	@Override
	public Account update(long id, Account accountRequest) {
		Account accountUpdate = accountRepo.getReferenceById(id);
		accountUpdate.setCode(accountRequest.getCode());
		accountUpdate.setLevel(accountRequest.getLevel());
		accountUpdate.setName(accountRequest.getName());
		accountUpdate.setUpdatedBy(getUserName());
		return accountRepo.save(accountUpdate);
	}

	@Override
	public void delete(long id) {
		accountRepo.deleteById(id);
	}

	@Override
	public Account updateByUUID(UUID genID, Account accountRequest) {
		Account accountUpdate = accountRepo.findByUUID(genID);
		accountUpdate.setCode(accountRequest.getCode());
		accountUpdate.setLevel(accountRequest.getLevel());
		accountUpdate.setName(accountRequest.getName());
		accountUpdate.setUpdatedBy(getUserName());
		return accountRepo.save(accountUpdate);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		Account accountUpdate = accountRepo.findByUUID(genID);
		if (accountUpdate.getId() != null)
			accountRepo.deleteById(accountUpdate.getId());
	}

}
