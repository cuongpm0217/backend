package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Account;

public interface AccountService {
	Account save(Account accountRequest);

	Account findById(long id);

	Account findByUUID(UUID genId);

	List<Account> getAll();

	Account update(long id, Account accountRequest);

	void delete(long id);

	Account updateByUUID(UUID genID, Account accountRequest);

	void deleteByUUID(UUID genID);
}
