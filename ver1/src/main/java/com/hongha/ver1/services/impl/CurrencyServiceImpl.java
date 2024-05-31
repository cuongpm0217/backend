package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.Currency;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.Role;
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
	public Currency save(Currency currencyRequest) {
		history = new History(Currency.class.getName(), EAction.CREATE, currencyRequest.getId().toString());
		historyRepo.save(history);
		return moneyRepo.save(currencyRequest);
	}

	@Override
	public Currency findById(long id) {
		return moneyRepo.getReferenceById(id);
	}

	@Override
	public Currency findByUUID(UUID genId) {
		return moneyRepo.findByUUID(genId);
	}

	@Override
	public List<Currency> getAll() {
		return moneyRepo.findAll();
	}

	@Override
	public Currency update(long id, Currency currencyRequest) {
		Currency updateObj = moneyRepo.getReferenceById(id);
		updateObj.setExchangeVND(currencyRequest.getExchangeVND());
		updateObj.setFullName(currencyRequest.getFullName());
		updateObj.setName(currencyRequest.getName());
		updateObj.setSymbol(currencyRequest.getSymbol());
		history = new History(Currency.class.getName(), EAction.UPDATE, updateObj.getId().toString());
		historyRepo.save(history);
		return moneyRepo.save(updateObj);
	}

	@Override
	public void delete(long id) {
		Currency updateObj = moneyRepo.getReferenceById(id);
		if (Objects.nonNull(updateObj)) {
			history = new History(Currency.class.getName(), EAction.DELETE, updateObj.getId().toString());
			historyRepo.save(history);
			moneyRepo.deleteById(updateObj.getId());
		}
	}

	@Override
	public Currency updateByUUID(UUID genID, Currency currencyRequest) {
		Currency updateObj = moneyRepo.findByUUID(genID);
		updateObj.setExchangeVND(currencyRequest.getExchangeVND());
		updateObj.setFullName(currencyRequest.getFullName());
		updateObj.setName(currencyRequest.getName());
		updateObj.setSymbol(currencyRequest.getSymbol());
		history = new History(Currency.class.getName(), EAction.UPDATE, updateObj.getGenId().toString());
		historyRepo.save(history);
		return moneyRepo.save(updateObj);
	}

	@Override
	public void deleteByUUID(UUID genID) {
		Currency updateObj = moneyRepo.findByUUID(genID);
		if (Objects.nonNull(updateObj)) {
			history = new History(Currency.class.getName(), EAction.DELETE, updateObj.getGenId().toString());
			historyRepo.save(history);
			moneyRepo.deleteById(updateObj.getId());
		}
	}

}
