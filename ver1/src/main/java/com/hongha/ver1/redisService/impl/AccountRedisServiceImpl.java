package com.hongha.ver1.redisService.impl;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongha.ver1.controllers.response.Pagination;
import com.hongha.ver1.dtos.AccountDTO;
import com.hongha.ver1.redisService.AccountRedisService;
import com.hongha.ver1.utils.GenerateCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountRedisServiceImpl implements AccountRedisService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ObjectMapper redisObjectMapper;

	@Override
	public List<AccountDTO> findBySearchText(String searchText, int pageNo, int pageSize, String sortBy,
			String sortType) throws JsonProcessingException, JsonMappingException {
		String key = GenerateCode.getKeyList(AccountDTO.class, searchText, pageNo, pageSize, sortBy, sortType);
		List<AccountDTO> result = null;
		if (redisTemplate.getExpire(key) > 0) {
			String json = (String) redisTemplate.opsForValue().get(key);
			if (json != null) {
				result = redisObjectMapper.readValue(json, new TypeReference<List<AccountDTO>>() {
				});
			}
		}else {
			log.info("key expired");
		}
		return result;
	}

	@Override
	public void saveAllAccount(List<AccountDTO> accounts, String searchText, int pageNo, int pageSize, String sortBy,
			String sortType) throws JsonProcessingException, JsonMappingException {
		String key = GenerateCode.getKeyList(AccountDTO.class, searchText, pageNo, pageSize, sortBy, sortType);
		String json = redisObjectMapper.writeValueAsString(accounts);
		Duration timeout = Duration.ofDays(1);
		redisTemplate.opsForValue().set(key, json, timeout);
	}

	@Override
	public void clearList() {
		Set<String> keys = redisTemplate.keys("allaccount");
		redisTemplate.delete(keys);
	}

	@Override
	public AccountDTO getOne(UUID uuid) throws JsonMappingException, JsonProcessingException {
		String key = GenerateCode.getKeyOne(AccountDTO.class, uuid);
		AccountDTO result = null;
		if (redisTemplate.getExpire(key) > 0) {
			String json = (String) redisTemplate.opsForValue().get(key);
			result = json != null ? redisObjectMapper.readValue(json, new TypeReference<AccountDTO>() {
			}) : null;
		}else {
			log.info("key expired");
		}
		return result;
	}

	@Override
	public void saveAccount(AccountDTO accountDTO) throws JsonProcessingException, JsonMappingException {
		String key = GenerateCode.getKeyOne(AccountDTO.class, accountDTO.getGenId());
		String json = redisObjectMapper.writeValueAsString(accountDTO);
		Duration timeout = Duration.ofDays(1);
		redisTemplate.opsForValue().set(key, json, timeout);

	}

	@Override
	public void clearOne(UUID uuid) {
		String key = GenerateCode.getKeyOne(AccountDTO.class, uuid);
		redisTemplate.delete(key);
	}

	@Override
	public Pagination getPagination(String searchText) {
		String key = GenerateCode.getKeyPagination(AccountDTO.class, searchText);
		Pagination result = null;
		if (redisTemplate.getExpire(key) > 0) {
			String json = (String) redisTemplate.opsForValue().get(key);
			try {
				result = json != null ? redisObjectMapper.readValue(json, new TypeReference<Pagination>() {
				}) : null;
			} catch (JsonMappingException e) {
				log.error("Wrong reference class");
			} catch (JsonProcessingException e) {
				log.error("Wrong key");
			}
		}else {
			log.info("key expired");
		}
		return result;
	}

	@Override
	public void savePagination(Pagination pagination, String searchText) {
		String key = GenerateCode.getKeyPagination(AccountDTO.class, searchText);
		String json = "";
		try {
			json = redisObjectMapper.writeValueAsString(pagination);
		} catch (JsonProcessingException e) {
			log.error("Can't write value redis");
		}
		Duration timeout = Duration.ofDays(1);
		redisTemplate.opsForValue().set(key, json, timeout);
	}

}
