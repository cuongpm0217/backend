package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Surrogate;

public interface ISurrogateService {
	Surrogate save(Surrogate surrogateRequest);

	Surrogate findById(long id);

	Surrogate findByUUID(UUID genId);

	List<Surrogate> getAll();

	Surrogate update(long id, Surrogate surrogateRequest);

	void delete(long id);

	Surrogate updateByUUID(UUID genID, Surrogate surrogateRequest);

	void deleteByUUID(UUID genID);
}
