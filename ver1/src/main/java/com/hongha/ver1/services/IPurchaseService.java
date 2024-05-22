package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Purchase;

public interface IPurchaseService {
	Purchase save(Purchase purchaseRequest);

	Purchase findById(long id);

	Purchase findByUUID(UUID genId);

	List<Purchase> getAll();

	Purchase update(long id, Purchase purchaseRequest);

	void delete(long id);

	Purchase updateByUUID(UUID genID, Purchase purchaseRequest);

	void deleteByUUID(UUID genID);
}
