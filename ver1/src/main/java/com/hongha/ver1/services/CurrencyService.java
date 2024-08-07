package com.hongha.ver1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.hongha.ver1.entities.Currency;

public interface CurrencyService {
	Currency save(Currency currencyRequest);

	Currency findById(long id);

	Currency findByUUID(UUID genId);

	List<Currency> getAll();

	Currency update(long id, Currency currencyRequest);

	boolean delete(long id);

	Currency updateByUUID(UUID genID, Currency currencyRequest);

	boolean deleteByUUID(UUID genID);

	Currency getExchangeVNDByCode(String code);

	Page<Currency> getAll(int pageNo, int pageSize, String sortBy, String sortType);

	Page<Currency> findByCodeContaining(String code, int pageNo, int pageSize, String sortBy, String sortType);
}
