package com.hongha.ver1.services.impl;

import java.net.URL;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hongha.ver1.entities.Currency;
import com.hongha.ver1.repositories.CurrencyRepository;
import com.hongha.ver1.services.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
	@Autowired
	private CurrencyRepository moneyRepo;

	@Override
	@Transactional
	public Currency save(Currency currencyRequest) {
		Currency isInserted = moneyRepo.save(currencyRequest);
		if (moneyRepo.getReferenceById(currencyRequest.getId()) != null) {
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
		Currency currency = moneyRepo.findByGenId(genId);
		if (currency != null) {
			return currency;
		} else {
			throw new RuntimeException("Not found");
		}
	}

	@Override
	@Scheduled(cron = "0 0 */4 ? * *")
	public List<Currency> getAll() {
		loadExchangeFromVCB();
		List<Currency> list = moneyRepo.findAll();
		return list;
	}

	@Override
	@Transactional
	public Currency update(long id, Currency currencyRequest) {
		Currency updateObj = moneyRepo.getReferenceById(id);
		if (updateObj != null) {
			return updateObj(currencyRequest, updateObj);
		} else {
			throw new RuntimeException("Not found " + id);
		}
	}

	private Currency updateObj(Currency currencyRequest, Currency updateObj) {
		updateObj.setExchangeVND(currencyRequest.getExchangeVND());
		updateObj.setFullName(currencyRequest.getFullName());
		updateObj.setCode(currencyRequest.getCode());
		updateObj.setSymbol(currencyRequest.getSymbol());
		return moneyRepo.save(updateObj);
	}

	@Override
	@Transactional
	public void delete(long id) {
		Currency updateObj = moneyRepo.getReferenceById(id);
		if (Objects.nonNull(updateObj)) {
			moneyRepo.deleteById(updateObj.getId());
		} else {
			throw new RuntimeException("Not found");
		}
	}

	@Override
	@Transactional
	public Currency updateByUUID(UUID genID, Currency currencyRequest) {
		Currency updateObj = moneyRepo.findByGenId(genID);
		if (updateObj != null) {
			return updateObj(currencyRequest, updateObj);
		} else {
			throw new RuntimeException("Not found " + genID);
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Currency updateObj = moneyRepo.findByGenId(genID);
		if (Objects.nonNull(updateObj)) {
			moneyRepo.deleteById(updateObj.getId());
		} else {
			throw new RuntimeException("Not found " + genID);
		}
	}
	@Scheduled(cron = "0 0 */4 ? * *")
	public void loadExchangeFromVCB() {
		moneyRepo.deleteAll();
		// default VND
		Currency vnd = new Currency();
		vnd.setCode("VND");
		vnd.setExchangeVND(1);
		vnd.setFullName("Việt Nam Đồng");
		vnd.setSymbol(java.util.Currency.getInstance("VND").getSymbol());
		moneyRepo.save(vnd);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(
					new URL("https://portal.vietcombank.com.vn/Usercontrols/TVPortal.TyGia/pXML.aspx").openStream());
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Exrate");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Currency currencyDB = new Currency();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String curCode = eElement.getAttributes().getNamedItem("CurrencyCode").getTextContent();
					String curName = eElement.getAttributes().getNamedItem("CurrencyName").getTextContent().trim();
					String strExRate = eElement.getAttributes().getNamedItem("Buy").getTextContent().replaceAll(",",
							"");
					if (!strExRate.contentEquals("-")) {
						double d = Math.floor(Double.valueOf(strExRate));
						currencyDB.setCode(curCode);
						currencyDB.setFullName(curName);
						currencyDB.setExchangeVND(d);
						currencyDB.setSymbol(java.util.Currency.getInstance(curCode).getSymbol());
					}
				}
				if (currencyDB.getCode() != null) {
					moneyRepo.saveAndFlush(currencyDB);
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	@Override
	public Currency getExchangeVNDByCode(String code) {
		Currency selected = moneyRepo.findByCode(code);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Currency:" + code);
		}
	}

	@Override
	
	public Page<Currency> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		loadExchangeFromVCB();
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("asc")) {// default descending >> USD no.1
			sort = sort.ascending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Currency> page = moneyRepo.findAll(pageable);
		return page;
	}
}
