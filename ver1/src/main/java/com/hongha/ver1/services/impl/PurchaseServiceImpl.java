package com.hongha.ver1.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Purchase;
import com.hongha.ver1.repositories.PurchaseRepository;
import com.hongha.ver1.services.PurchaseService;
import com.hongha.ver1.utils.GenerateCode;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseRepository purRepo;

	@Override
	@Transactional
	public Purchase save(Purchase purRequest) {
		// mq generate purchase code
		int countInYear = getCountInYearUsingJPQL();
		String code = GenerateCode.GenInvoiceCode(countInYear, Purchase.class.getName());
		purRequest.setCode(code);
		// mq insert then generate code
		Purchase isInserted = purRepo.save(purRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Purchase");
		}

	}

	@Override
	public Purchase findById(long id) {
		Purchase selected = purRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(id));
		}
	}

	@Override
	public Purchase findByUUID(UUID genId) {
		Purchase selected = purRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Purchase> getAll() {
		return purRepo.findAll();
	}

	@Override
	@Transactional
	public Purchase update(long id, Purchase purRequest) {
		Purchase selected = purRepo.getReferenceById(id);
		if (selected != null) {
			updateObj(purRequest, selected);
			return updateObj(purRequest, selected);

		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(id));
		}
	}

	private Purchase updateObj(Purchase purRequest, Purchase selected) {
		selected.setBranchId(purRequest.getBranchId());
		selected.setCode(purRequest.getCode());
		selected.setDetail(purRequest.getDetail());
		selected.setEmployeeId(purRequest.getEmployeeId());
		selected.setPartnerId(purRequest.getPartnerId());
		Purchase updated = purRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Purchase:" + purRequest.getId());
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		Purchase selected = purRepo.getReferenceById(id);
		if (selected != null) {
			purRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Purchase updateByUUID(UUID genID, Purchase purRequest) {
		Purchase selected = purRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(purRequest, selected);
		} else {
			throw new RuntimeException("Not found Purchase:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		Purchase selected = purRepo.findByGenId(genID);
		if (selected != null) {
			purRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<Purchase> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Purchase> page = purRepo.findAll(pageable);
		return page;
	}

	@Override
	public Page<Purchase> findByPartnerIdAndCreateAtBetween(long partnerId, Date fromDate, Date toDate, int pageNo,
			int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Purchase> page = purRepo.findByPartnerIdAndCreatedAtBetween(partnerId, fromDate, toDate, pageable);
		return page;
	}

	@Override
	public Page<Purchase> findByCreatedAtBetween(Date fromDate, Date toDate, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Purchase> page = purRepo.findByCreatedAtBetween(fromDate, toDate, pageable);
		return page;
	}

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
				.createQuery("select count(p.id) from _purchase p where year(p.created_at)=year(now())");
		countInYear = (int) query.getSingleResult();
		return countInYear;
	}
}
