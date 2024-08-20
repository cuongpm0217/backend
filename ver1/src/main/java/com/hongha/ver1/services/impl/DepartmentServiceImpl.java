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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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
		Department isInserted;
		if (departmentRequest.getGenId() != null) {
			isInserted = depRepo.save(departmentRequest);
		} else {
			// fix UUID null
			Department clone = new Department();
			clone.setBranchId(departmentRequest.getBranchId());
			clone.setCode(departmentRequest.getCode());
			clone.setName(departmentRequest.getName());
			clone.setVname(departmentRequest.getVname());
			isInserted = depRepo.save(clone);
		}
		return isInserted;

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
			selected.setBranchId(departmentRequest.getBranchId());
			selected.setCode(departmentRequest.getCode());
			selected.setName(departmentRequest.getName());
			selected.setVname(departmentRequest.getVname());
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
	public Page<Department> findBySearchText(String searchText, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Department> page = depRepo.findBySearchText(searchText, pageable);
		return page;
	}

	@Override
	public Page<Department> getAll(int pageNo, int pageSize, String sortBy, String sortType) throws IOException {
		if (depRepo.count() == 0) {
			loadDepartmentExcel();
		}
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Department> page = depRepo.findAll(pageable);
		return page;
	}

	@Override
	public Page<Department> getByBranch(long branchId, int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Department> page = depRepo.findByBranchId(branchId, pageable);
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
					dep.setBranchId(new BigDecimal((double) cellValue).longValue());
					break;
				case 3:
					dep.setCode((String) getCellValue(cell));
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
