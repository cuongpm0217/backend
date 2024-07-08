package com.hongha.ver1.services;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Surrogate;

public interface SurrogateService {
	Surrogate save(Surrogate surrogateRequest);

	Surrogate findById(long id);

	Surrogate findByUUID(UUID genId);

	Surrogate update(long id, Surrogate surrogateRequest);

	boolean delete(long id);

	Surrogate updateByUUID(UUID genID, Surrogate surrogateRequest);

	boolean deleteByUUID(UUID genID);

	Page<Surrogate> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Surrogate> findByName(String name, int pageNo, int pageSize, String sortBy, String sortType);

	Page<Surrogate> findByPhone(String phone, int pageNo, int pageSize, String sortBy, String sortType);

	Page<Surrogate> findByCustomerId(long customerId, int pageNo, int pageSize, String sortBy, String sortType);
}
