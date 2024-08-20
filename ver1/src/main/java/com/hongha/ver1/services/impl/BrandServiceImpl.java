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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Brand;
import com.hongha.ver1.repositories.BrandRepository;
import com.hongha.ver1.services.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository brandRepo;

	@Override
	@Transactional
	public Brand save(Brand proBrandRequest) {
		Brand isInserted = brandRepo.save(proBrandRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create ProductBrand");
		}

	}

	@Override
	public Brand findById(long id) {
		Brand selected = brandRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(id));
		}
	}

	@Override
	public Brand findByUUID(UUID genId) {
		Brand selected = brandRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProductBrand:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Brand> getAll() throws IOException {
		List<Brand> list = brandRepo.findAll();
		if (list.isEmpty()) {
			loadBrandExcel();
		}
		return list;
	}

	@Override
	@Transactional
	public Brand update(long id, Brand proBrandRequest) {
		Brand selected = brandRepo.getReferenceById(id);
		if (selected != null) {
			selected.setName(proBrandRequest.getName());
			Brand updated = brandRepo.save(selected);
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
	public boolean delete(long id) {
		Brand selected = brandRepo.getReferenceById(id);
		if (selected != null) {
			brandRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Brand updateByUUID(UUID genID, Brand proBrandRequest) {
		Brand selected = brandRepo.findByGenId(genID);
		if (selected != null) {
			selected.setName(proBrandRequest.getName());
			Brand updated = brandRepo.save(selected);
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
	public boolean deleteByUUID(UUID genID) {
		Brand selected = brandRepo.findByGenId(genID);
		if (selected != null) {
			brandRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<Brand> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sorted = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sorted = sorted.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sorted);
		Page<Brand> page = brandRepo.findAll(pageable);
		if (page.isEmpty()) {
			try {
				loadBrandExcel();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	@Override
	public Page<Brand> findByNameLike(String name, int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Brand> page = brandRepo.findByNameLike(name, pageable);
		return page;
	}

	// Load data sample
	private void loadBrandExcel() throws IOException {
		String excelFilePath = "src/main/resources/static/data.xlsx";

		InputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("brand");
		for (Row nextRow : sheet) {
			//				 Get all cells
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			// Read cells and set value for object
			Brand proBrand = new Brand();
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
				default:
					break;
				}
			}
			brandRepo.save(proBrand);
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
