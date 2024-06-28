package com.hongha.ver1.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Account;
import com.hongha.ver1.repositories.AccountRepository;
import com.hongha.ver1.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepo;

	@Override
	@Transactional
	public Account save(Account accountRequest) {
		Account isInserted = accountRepo.save(accountRequest);
		if (isInserted != null) {

			return isInserted;
		} else {
			throw new RuntimeException("Can't save");
		}
	}

	@Override
	public Account findById(long id) {
		Account acc = accountRepo.getReferenceById(id);
		if (acc != null) {
			return acc;
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(id));
		}
	}

	@Override
	public Account findByUUID(UUID genId) {
		Account acc = accountRepo.findByGenId(genId);
		if (acc != null) {
			return acc;
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(genId));
		}
	}
	@Override
	public Page<Account> findByCodeContaining(String code, int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sorted = Sort.by(sortBy);
		if(sortType.equals("des")) {
			sorted = Sort.by(sortBy).descending();
		}
		Pageable paging = PageRequest.of(pageNo, pageSize, sorted);
		Page<Account> result = accountRepo.findByCodeContaining(code,paging);
		return result;
	}
	
	@Override
	public Page<Account> getAll(int pageNo, int pageSize, String sortBy, String sortType) throws IOException {
		long counter = accountRepo.count();
		if (counter == 0) {
			loadAccountExcel();
		}
		Sort sorted = Sort.by(sortBy);
		if(sortType.equals("des")) {
			sorted = sorted.descending();
		}
		Pageable paging = PageRequest.of(pageNo, pageSize, sorted);
		Page<Account> pageResult = accountRepo.findAll(paging);		
		return pageResult;
	}

	@Override
	@Transactional
	public Account update(long id, Account accountRequest) {
		Account accountUpdate = accountRepo.getReferenceById(id);
		if (accountUpdate != null) {
			accountUpdate.setCode(accountRequest.getCode());
			accountUpdate.setLevel(accountRequest.getLevel());
			accountUpdate.setName(accountRequest.getName());
			Account isUpdated = accountRepo.save(accountUpdate);
			if (isUpdated != null) {
				return isUpdated;
			} else {
				throw new RuntimeException("Update fail");
			}
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Account acc = accountRepo.getReferenceById(id);
		if (acc != null) {
			accountRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Account updateByUUID(UUID genID, Account accountRequest) {
		Account accountUpdate = accountRepo.findByGenId(genID);
		if (accountUpdate != null) {
			accountUpdate.setCode(accountRequest.getCode());
			accountUpdate.setLevel(accountRequest.getLevel());
			accountUpdate.setName(accountRequest.getName());
			Account isUpdated = accountRepo.save(accountUpdate);
			if (isUpdated != null) {
				return isUpdated;
			} else {
				throw new RuntimeException("Update fail");
			}
		} else {
			throw new RuntimeException("Not found:" + genID);
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Account accountUpdate = accountRepo.findByGenId(genID);
		if (accountUpdate != null) {
			accountRepo.deleteById(accountUpdate.getId());
		} else {
			throw new RuntimeException("Not found " + genID);
		}
	}

// Load data sample
	private void loadAccountExcel() throws IOException {
		String excelFilePath = "src/main/resources/static/data.xlsx";

		InputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("account");
		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
			Row nextRow = rows.next();
//			 Get all cells
			Iterator<Cell> cellIterator = nextRow.cellIterator();
// Read cells and set value for object
			Account acc = new Account();
			while (cellIterator.hasNext()) {
				// Read cell
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				if (cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				// Set value for object
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					acc.setCode(new BigDecimal((double) cellValue).toString());
					break;
				case 1:
					acc.setName((String) getCellValue(cell));
					break;
				case 2:
					acc.setLevel(new BigDecimal((double) cellValue).intValue());
					break;

				default:
					break;
				}
			}
			accountRepo.save(acc);
		}
		wb.close();
		inputStream.close();
	}

	private Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		Object cellValue = null;
		switch (cellType) {
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
		default:
			break;
		}
		return cellValue;
	}
}
