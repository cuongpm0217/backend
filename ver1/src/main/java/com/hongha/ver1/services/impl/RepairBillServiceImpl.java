package com.hongha.ver1.services.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.RepairBill;
import com.hongha.ver1.repositories.RepairBillRepository;
import com.hongha.ver1.services.RepairBillService;
import com.hongha.ver1.utils.GenerateCode;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class RepairBillServiceImpl implements RepairBillService {
	@Autowired
	private RepairBillRepository billRepo;

	@Override
	@Transactional
	public RepairBill save(RepairBill billRequest) {
		int countInYear = getCountInYearUsingJPQL();
		String code = GenerateCode.GenInvoiceCode(countInYear, RepairBill.class.getName());
		billRequest.setCode(code);
		RepairBill isInserted = billRepo.save(billRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create RepairBill");
		}

	}

	@Override
	public RepairBill findById(long id) {
		RepairBill selected = billRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(id));
		}
	}

	@Override
	public RepairBill findByUUID(UUID genId) {
		RepairBill selected = billRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(genId));
		}
	}

	@Override
	@Transactional
	public RepairBill update(long id, RepairBill billRequest) {
		RepairBill selected = billRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(billRequest, selected);
			return updateObj(billRequest, selected);
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(id));
		}
	}

	private RepairBill updateObj(RepairBill billRequest, RepairBill selected) {
		selected.setBranchId(billRequest.getBranchId());
		selected.setCode(billRequest.getCode());
		selected.setCustomerId(billRequest.getCustomerId());
		selected.setDetail(billRequest.getDetail());
		selected.setEmployeeId(billRequest.getEmployeeId());
		selected.setSurrogateId(billRequest.getSurrogateId());
		selected.setVehicleId(billRequest.getVehicleId());
		selected.setStartedDate(billRequest.getStartedDate());
		selected.setEndDate(billRequest.getEndDate());
		RepairBill updated = billRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update RepairBill:" + billRequest.getId());
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		RepairBill selected = billRepo.getReferenceById(id);
		if (selected != null) {
			billRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public RepairBill updateByUUID(UUID genID, RepairBill billRequest) {
		RepairBill selected = billRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(billRequest, selected);
		} else {
			throw new RuntimeException("Not found RepairBill:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		RepairBill selected = billRepo.findByGenId(genID);
		if (selected != null) {
			billRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<RepairBill> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<RepairBill> page = billRepo.findAll(pageable);
		return page;
	}

	@Override
	public Page<RepairBill> findByStartedDateBetween(Date fromDate, Date toDate, int pageNo, int pageSize,
			String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<RepairBill> page = billRepo.findByStartedDateBetween(fromDate, toDate, pageable);
		return page;
	}

//	@Override
//	public Page<RepairBill> findByLicensePlate(String licensePlate, int pageNo, int pageSize, String sortBy,
//			String sortType) {
//		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
//		Page<RepairBill> page = billRepo.findByLicensePlate(licensePlate, pageable);
//		return page;
//	}

	private Pageable genPageable(int pageNo, int pageSize, String sortBy, String sortType) {
		Sort sort = Sort.by(sortBy);
		if (sortType.equals("des")) {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return pageable;
	}

	private int getCountInYearUsingJPQL() {
		int countInYear = 0;
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		Query query = entityManager
				.createQuery("select count(r.id) from _repair_bill r where year(r.started_date)=year(now())");
		countInYear = (int) query.getSingleResult();
		return countInYear;
	}
}
