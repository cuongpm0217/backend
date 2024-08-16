package com.hongha.ver1.redisService;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hongha.ver1.controllers.response.Pagination;
import com.hongha.ver1.dtos.AccountDTO;

public interface AccountRedisService {

	List<AccountDTO> findBySearchText(String searchText, int pageNo, int pageSize, String sortBy, String sortType)
			throws JsonProcessingException, JsonMappingException;

	void saveAllAccount(List<AccountDTO> accounts, String searchText, int pageNo, int pageSize, String sortBy,
			String sortType) throws JsonProcessingException, JsonMappingException;

	Pagination getPagination(String searchText);

	void savePagination(Pagination pagination, String searchText);

	AccountDTO getOne(UUID uuid) throws JsonProcessingException, JsonMappingException;

	void saveAccount(AccountDTO accountDTO) throws JsonProcessingException, JsonMappingException;

	void clearList();

	void clearOne(UUID uuid);

}
