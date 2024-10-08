package com.hongha.ver1.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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

import com.hongha.ver1.entities.Position;
import com.hongha.ver1.repositories.PositionRepository;
import com.hongha.ver1.services.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionRepository positionRepo;

	@Override
	@Transactional
	public Position save(Position positionRequest) {
		Position isInserted;
		if (positionRequest.getGenId() != null) {
			isInserted = positionRepo.save(positionRequest);
		} else {
			Position clone = new Position();
			clone.setLevel(positionRequest.getLevel());
			clone.setName(positionRequest.getName());
			clone.setVname(positionRequest.getVname());
			isInserted = positionRepo.save(clone);
		}
		return isInserted;

	}

	@Override
	public Position findById(long id) {
		Position selected = positionRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Position:" + String.valueOf(id));
		}
	}

	@Override
	public Position findByUUID(UUID genId) {
		Position selected = positionRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Position:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Position> getAll() throws IOException {
		List<Position> list = positionRepo.findAll();
		if (list.isEmpty()) {
			loadPositionExcel();
		}
		return list;
	}

	@Override
	@Transactional
	public Position update(long id, Position positionRequest) {
		Position selected = positionRepo.getReferenceById(id);
		if (selected != null) {
			selected.setLevel(positionRequest.getLevel());
			selected.setName(positionRequest.getName());
			Position updated = positionRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Position:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Position:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		Position selected = positionRepo.getReferenceById(id);
		if (selected != null) {
			positionRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Position updateByUUID(UUID genID, Position positionRequest) {
		Position selected = positionRepo.findByGenId(genID);
		if (selected != null) {
			selected.setLevel(positionRequest.getLevel());
			selected.setName(positionRequest.getName());
			Position updated = positionRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Position:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Position:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		Position selected = positionRepo.findByGenId(genID);
		if (selected != null) {
			positionRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<Position> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Position> page = positionRepo.findAll(pageable);
		if (!page.hasContent()) {

			try {
				loadPositionExcel();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	@Override
	public Page<Position> findBySearchText(String searchText, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Position> page = positionRepo.findBySearchText(searchText, pageable);
		return page;
	}

	private Pageable genPageable(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.startsWith("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return pageable;
	}

	// Load data sample
	private void loadPositionExcel() throws IOException {
		String excelFilePath = "src/main/resources/static/data.xlsx";
		InputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet("position");
		for (Row nextRow : sheet) {
			//				 Get all cells
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			// Read cells and set value for object
			Position position = new Position();
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
					position.setName((String) getCellValue(cell));
					break;
				case 1:
					position.setVname((String) getCellValue(cell));
					break;
				case 2:
					position.setLevel(new BigDecimal((double) cellValue).intValue());
					break;
				default:
					break;
				}
			}
			positionRepo.save(position);
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
