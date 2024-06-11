package com.hongha.ver1.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.ProductBrand;
import com.hongha.ver1.repositories.ProductBrandRepository;
import com.hongha.ver1.services.ProductBrandService;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {
	@Autowired
	private ProductBrandRepository proBrandRepo;

	@Override
	@Transactional
	public ProductBrand save(ProductBrand proBrandRequest) {
		ProductBrand isInserted = proBrandRepo.save(proBrandRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create ProductBrand");
		}

	}

	@Override
	public ProductBrand findById(long id) {
		ProductBrand selected = proBrandRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(id));
		}
	}

	@Override
	public ProductBrand findByUUID(UUID genId) {
		ProductBrand selected = proBrandRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(genId));
		}
	}

	@Override
	public List<ProductBrand> getAll() throws IOException {
		List<ProductBrand> list = proBrandRepo.findAll();
		if (list.isEmpty()) {
			loadBrandExcel();
		}
		return list;
	}

	@Override
	@Transactional
	public ProductBrand update(long id, ProductBrand proBrandRequest) {
		ProductBrand selected = proBrandRepo.getReferenceById(id);
		if (selected != null) {
			selected.setCountry(proBrandRequest.getCountry());
			selected.setName(proBrandRequest.getName());
			ProductBrand updated = proBrandRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update ProductBrand:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		ProductBrand selected = proBrandRepo.getReferenceById(id);
		if (selected != null) {
			proBrandRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public ProductBrand updateByUUID(UUID genID, ProductBrand proBrandRequest) {
		ProductBrand selected = proBrandRepo.findByUUID(genID);
		if (selected != null) {
			selected.setCountry(proBrandRequest.getCountry());
			selected.setName(proBrandRequest.getName());
			ProductBrand updated = proBrandRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update ProductBrand:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		ProductBrand selected = proBrandRepo.findByUUID(genID);
		if (selected != null) {
			proBrandRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(genID));
		}
	}

	// Load data sample
	private void loadBrandExcel() throws IOException {
		String excelFilePath = "src/main/resources/static/data.xlsx";

		InputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("brand");
		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
			Row nextRow = rows.next();
//				 Get all cells
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			// Read cells and set value for object
			ProductBrand proBrand = new ProductBrand();
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
					proBrand.setName((String) getCellValue(cell));
					break;
				case 1:
					proBrand.setCountry((String) getCellValue(cell));
					break;
				default:
					break;
				}
			}
			proBrandRepo.save(proBrand);
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
