package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Currency;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.repositories.CurrencyRepository;
import com.hongha.ver1.repositories.HistoryRepository;
import com.hongha.ver1.services.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	@Autowired
	private CurrencyRepository moneyRepo;
	@Autowired
	private HistoryRepository historyRepo;
	private History history;

	@Override
	@Transactional
	public Currency save(Currency currencyRequest) {
		Currency isInserted = moneyRepo.save(currencyRequest);
		if (moneyRepo.getReferenceById(currencyRequest.getId()) != null) {
			history = new History(Currency.class.getName(), EAction.CREATE, currencyRequest.getId().toString());
			historyRepo.save(history);
			return isInserted;
		} else {
			throw new RuntimeException("Can't save");
		}
	}

	@Override
	public Currency findById(long id) {
		Currency currency = moneyRepo.getReferenceById(id);
		if (currency != null) {
			return currency;
		} else {
			throw new RuntimeException("Not found");
		}
	}

	@Override
	public Currency findByUUID(UUID genId) {
		Currency currency = moneyRepo.findByUUID(genId);
		if (currency != null) {
			return currency;
		} else {
			throw new RuntimeException("Not found");
		}
	}

	@Override
	public List<Currency> getAll() {
		List<Currency> list = moneyRepo.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new RuntimeException("List is empty");
		}
	}

	
	@Override
	@Transactional
	public Currency update(long id, Currency currencyRequest) {
		Currency updateObj = moneyRepo.getReferenceById(id);
		if (updateObj != null) {
			updateObj.setExchangeVND(currencyRequest.getExchangeVND());
			updateObj.setFullName(currencyRequest.getFullName());
			updateObj.setName(currencyRequest.getName());
			updateObj.setSymbol(currencyRequest.getSymbol());
			history = new History(Currency.class.getName(), EAction.UPDATE, updateObj.getId().toString());
			historyRepo.save(history);
			return moneyRepo.save(updateObj);
		} else {
			throw new RuntimeException("Not found " + id);
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Currency updateObj = moneyRepo.getReferenceById(id);
		if (Objects.nonNull(updateObj)) {
			history = new History(Currency.class.getName(), EAction.DELETE, updateObj.getId().toString());
			historyRepo.save(history);
			moneyRepo.deleteById(updateObj.getId());
		} else {
			throw new RuntimeException("Not found");
		}
	}
	
	@Override
	@Transactional
	public Currency updateByUUID(UUID genID, Currency currencyRequest) {
		Currency updateObj = moneyRepo.findByUUID(genID);
		if (updateObj != null) {
			updateObj.setExchangeVND(currencyRequest.getExchangeVND());
			updateObj.setFullName(currencyRequest.getFullName());
			updateObj.setName(currencyRequest.getName());
			updateObj.setSymbol(currencyRequest.getSymbol());
			history = new History(Currency.class.getName(), EAction.UPDATE, updateObj.getGenId().toString());
			historyRepo.save(history);
			return moneyRepo.save(updateObj);
		} else {
			throw new RuntimeException("Not found " + genID);
		}
	}
	
	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Currency updateObj = moneyRepo.findByUUID(genID);
		if (Objects.nonNull(updateObj)) {
			history = new History(Currency.class.getName(), EAction.DELETE, updateObj.getGenId().toString());
			historyRepo.save(history);
			moneyRepo.deleteById(updateObj.getId());
		} else {
			throw new RuntimeException("Not found " + genID);
		}
	}

}
