package com.hongha.ver1.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import com.hongha.ver1.entities.Purchase;

public interface PurchaseService {
	Purchase save(Purchase purchaseRequest);

	Purchase findById(long id);

	Purchase findByUUID(UUID genId);

	List<Purchase> getAll();

	Purchase update(long id, Purchase purchaseRequest);

	void delete(long id);

	Purchase updateByUUID(UUID genID, Purchase purchaseRequest);

	void deleteByUUID(UUID genID);

	Page<Purchase> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Purchase> findByPartnerIdAndCreateAtBetween(long partnerId, Date fromDate, Date toDate, int pageNo,
			int pageSize, String sortBy, String sortType);

	Page<Purchase> findByCreateAtBetween(Date fromDate, Date toDate, int pageNo, int pageSize, String sortBy,
			String sortType);
}
