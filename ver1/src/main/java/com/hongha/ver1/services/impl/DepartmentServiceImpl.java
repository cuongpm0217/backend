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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Department;
import com.hongha.ver1.repositories.DepartmentRepository;
import com.hongha.ver1.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository depRepo;

	@Override
	@Transactional
	public Department save(Department departmentRequest) {
		Department isInserted = depRepo.save(departmentRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Department");
		}

	}

	@Override
	public Department findById(long id) {
		Department selected = depRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(id));
		}
	}

	@Override
	public Department findByUUID(UUID genId) {
		Department selected = depRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Department> getAll() throws IOException {
		List<Department> list = depRepo.findAll();
		if (list.isEmpty()) {
			loadDepartmentExcel();
		}
		return list;
	}

	@Override
	@Transactional
	public Department update(long id, Department departmentRequest) {
		Department selected = depRepo.getReferenceById(id);
		if (selected != null) {
			selected.setCode(departmentRequest.getCode());
			selected.setName(departmentRequest.getName());
			Department updated = depRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Department:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		Department selected = depRepo.getReferenceById(id);
		if (selected != null) {
			depRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Department updateByUUID(UUID genID, Department departmentRequest) {
		Department selected = depRepo.findByGenId(genID);
		if (selected != null) {
			selected.setCode(departmentRequest.getCode());
			selected.setName(departmentRequest.getName());
			Department updated = depRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Department:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Department:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		Department selected = depRepo.findByGenId(genID);
		if (selected != null) {
			depRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Slice<Department> findByBranchIdAndVnameLike(long branchId, String vName, int pageNo, int pageSize,
			String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Slice<Department> page = depRepo.findByBranchIdAndVnameLike(branchId, vName, pageable);
		return page;
	}

	@Override
	public Slice<Department> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Slice<Department> page = depRepo.findAll(pageable);
		if(!page.hasContent()) {
			try {
				loadDepartmentExcel();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	// Load data sample
	private void loadDepartmentExcel() throws IOException {
		String excelFilePath = "src/main/resources/static/data.xlsx";

		InputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("department");
		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
			Row nextRow = rows.next();
//				 Get all cells
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			// Read cells and set value for object
			Department dep = new Department();
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
					dep.setName((String) getCellValue(cell));
					break;
				case 1:
					dep.setVname((String) getCellValue(cell));
					break;
				case 2:
					dep.setBranchId((long) cellValue);
					break;
				case 3:
					dep.setCode((String)getCellValue(cell));
					break;
				default:
					break;
				}
			}
			depRepo.save(dep);
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
