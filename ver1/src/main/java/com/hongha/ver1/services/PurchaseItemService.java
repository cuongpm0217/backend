package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.PurchaseItem;

public interface PurchaseItemService {
	PurchaseItem save(PurchaseItem purchaseItemRequest);

	PurchaseItem findById(long id);

	PurchaseItem findByUUID(UUID genId);

	List<PurchaseItem> getAll();

	PurchaseItem update(long id, PurchaseItem purchaseItemRequest);

	void delete(long id);

	PurchaseItem updateByUUID(UUID genID, PurchaseItem purchaseItemRequest);

	void deleteByUUID(UUID genID);
}
