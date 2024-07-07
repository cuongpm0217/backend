package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Partner;
import com.hongha.ver1.repositories.PartnerRepository;
import com.hongha.ver1.services.PartnerService;

@Service
public class PartnerServiceImpl implements PartnerService {
	@Autowired
	private PartnerRepository partnerRepo;

	@Override
	@Transactional
	public Partner save(Partner partnerRequest) {
		Partner isInserted = partnerRepo.save(partnerRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Partner");
		}

	}

	@Override
	public Partner findById(long id) {
		Partner selected = partnerRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Partner:" + String.valueOf(id));
		}
	}

	@Override
	public Partner findByUUID(UUID genId) {
		Partner selected = partnerRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Partner:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Partner> getAll() {
		return partnerRepo.findAll();
	}

	@Override
	@Transactional
	public Partner update(long id, Partner partnerRequest) {
		Partner selected = partnerRepo.getReferenceById(id);
		if (selected != null) {
			selected.setAddress1(partnerRequest.getAddress1());
			selected.setAddress2(partnerRequest.getAddress2());
			selected.setBankId(partnerRequest.getBankId());
			selected.setBankAccountNo(partnerRequest.getBankAccountNo());
			selected.setName(partnerRequest.getName());
			selected.setPhone1(partnerRequest.getPhone1());
			selected.setPhone2(partnerRequest.getPhone2());
			Partner updated = partnerRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Partner:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Partner:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Partner selected = partnerRepo.getReferenceById(id);
		if (selected != null) {
			partnerRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Partner:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Partner updateByUUID(UUID genID, Partner partnerRequest) {
		Partner selected = partnerRepo.findByGenId(genID);
		if (selected != null) {
			selected.setAddress1(partnerRequest.getAddress1());
			selected.setAddress2(partnerRequest.getAddress2());
			selected.setBankId(partnerRequest.getBankId());
			selected.setBankAccountNo(partnerRequest.getBankAccountNo());
			selected.setName(partnerRequest.getName());
			selected.setPhone1(partnerRequest.getPhone1());
			selected.setPhone2(partnerRequest.getPhone2());
			Partner updated = partnerRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Partner:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Partner:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Partner selected = partnerRepo.findByGenId(genID);
		if (selected != null) {
			partnerRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Partner:" + String.valueOf(genID));
		}
	}

	@Override
	public Page<Partner> findByPhone1OrPhone2Like(String phone1,String phone2, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Partner> page = partnerRepo.findByPhone1OrPhone2Like(phone1,phone2, pageable);
		return page;
	}

	@Override
	public Page<Partner> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Partner> page = partnerRepo.findAll(pageable);
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
}
