package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import com.hongha.ver1.entities.Currency;

public interface ICurrencyService {
	Currency save(Currency currencyRequest);

	Currency findById(long id);

	Currency findByUUID(UUID genId);

	List<Currency> getAll();

	Currency update(long id, Currency currencyRequest);

	void delete(long id);

	Currency updateByUUID(UUID genID, Currency currencyRequest);

	void deleteByUUID(UUID genID);
}
