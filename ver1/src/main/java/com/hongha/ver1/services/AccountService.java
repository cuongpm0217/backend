package com.hongha.ver1.services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Account;

public interface AccountService {
	Account save(Account accountRequest);

	Account findById(long id);

	Account findByUUID(UUID genId);

	Account update(long id, Account accountRequest);

	Account updateByUUID(UUID genID, Account accountRequest);

	boolean delete(long id);

	boolean deleteByUUID(UUID genID);

	Page<Account> getAll(int pageNo, int pageSize, String sortBy, String sortType) throws IOException;

	Page<Account> findBySearchText(String searchText, int pageNo, int pageSize, String sortBy, String sortType);

}
