package com.hongha.ver1.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hongha.ver1.dtos.AccountDTO;
import com.hongha.ver1.entities.Account;
import com.hongha.ver1.services.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private ModelMapper mapper;
//	@Autowired
//	private UserDetailsService userService;
	@Autowired
	private AccountService accountService;

	@GetMapping("/list")
	@ResponseBody
	public List<AccountDTO> getAll() throws IOException {
		List<Account> accounts = accountService.getAll();
		if (accounts.isEmpty()) {
			loadAccountExcel();
		}

		return accounts.stream().map(account -> mapper.map(account, AccountDTO.class)).collect(Collectors.toList());
	}

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

			accountService.save(acc);
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

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AccountDTO save(@RequestBody AccountDTO accountDTO) {
		Account account = mapper.map(accountDTO, Account.class);
		Account accountCreated = accountService.save(account);
		return mapper.map(accountCreated, AccountDTO.class);
	}

	@GetMapping("/id={id}")
	@ResponseBody
	public AccountDTO getOne(@PathVariable("id") long id) {
		AccountDTO accDTO = mapper.map(accountService.findById(id), AccountDTO.class);
		return accDTO;
	}

	@GetMapping("/uuid={uuid}")
	@ResponseBody
	public AccountDTO getOneByUUID(@PathVariable("uuid") UUID uuid) {
		return mapper.map(accountService.findByUUID(uuid), AccountDTO.class);
	}

	@PutMapping("/update-id={id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") long id, @RequestBody AccountDTO accountDTO) {
		if (!Objects.equals(id, accountDTO.getId())) {
			throw new IllegalArgumentException("IDs don't match");
		}
		Account account = mapper.map(accountDTO, Account.class);
		accountService.update(id, account);
	}

	@DeleteMapping("/delete-id={id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") long id) {
		accountService.delete(id);
	}

	@PutMapping("/update-acc={uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void updateByUUID(@PathVariable("uuid") UUID uuid, @RequestBody AccountDTO accountDTO) {
		if (!Objects.equals(uuid, accountDTO.getGenId())) {
			throw new IllegalArgumentException("UUIDs don't match");
		}
		Account account = mapper.map(accountDTO, Account.class);
		accountService.updateByUUID(uuid, account);
	}

	@DeleteMapping("/delete-acc={uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByUUID(@PathVariable("uuid") UUID uuid) {
		accountService.deleteByUUID(uuid);
	}
}
