package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Partner;

public interface PartnerService {
	Partner save(Partner partnerRequest);

	Partner findById(long id);

	Partner findByUUID(UUID genId);

	List<Partner> getAll();

	Partner update(long id, Partner partnerRequest);

	void delete(long id);

	Partner updateByUUID(UUID genID, Partner partnerRequest);

	void deleteByUUID(UUID genID);

	Page<Partner> findByPhone1OrPhone2Like(String phone, int pageNo, int pageSize, String sortBy, String sortType);

	Page<Partner> getAll(int pageNo, int pageSize, String sortBy, String sortType);
}
