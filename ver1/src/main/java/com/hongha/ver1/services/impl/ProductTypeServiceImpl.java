package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.ProductType;
import com.hongha.ver1.repositories.ProductTypeRepository;
import com.hongha.ver1.services.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	private ProductTypeRepository proTypeRepo;

	@Override
	@Transactional
	public ProductType save(ProductType proTypeRequest) {
		ProductType isInserted = proTypeRepo.save(proTypeRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create ProductType");
		}

	}

	@Override
	public ProductType findById(long id) {
		ProductType selected = proTypeRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProductType:" + String.valueOf(id));
		}
	}

	@Override
	public ProductType findByUUID(UUID genId) {
		ProductType selected = proTypeRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found ProductType:" + String.valueOf(genId));
		}
	}

	@Override
	public List<ProductType> getAll() {
		return proTypeRepo.findAll();
	}

	@Override
	@Transactional
	public ProductType update(long id, ProductType proTypeRequest) {
		ProductType selected = proTypeRepo.getReferenceById(id);
		if (selected != null) {
			selected.setName(proTypeRequest.getName());
			ProductType updated = proTypeRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update ProductType:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found ProductType:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		ProductType selected = proTypeRepo.getReferenceById(id);
		if (selected != null) {
			proTypeRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found ProductType:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public ProductType updateByUUID(UUID genID, ProductType proTypeRequest) {
		ProductType selected = proTypeRepo.findByUUID(genID);
		if (selected != null) {
			selected.setName(proTypeRequest.getName());
			ProductType updated = proTypeRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update ProductType:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found ProductType:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		ProductType selected = proTypeRepo.findByUUID(genID);
		if (selected != null) {
			proTypeRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found ProductType:" + String.valueOf(genID));
		}
	}
}
