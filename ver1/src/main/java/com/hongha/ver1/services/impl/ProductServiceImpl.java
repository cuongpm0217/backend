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

import com.hongha.ver1.entities.Product;
import com.hongha.ver1.repositories.ProductRepository;
import com.hongha.ver1.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Override
	@Transactional
	public Product save(Product productRequest) {
		Product isInserted = productRepo.save(productRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Product");
		}

	}

	@Override
	public Product findById(long id) {
		Product selected = productRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Product:" + String.valueOf(id));
		}
	}

	@Override
	public Product findByUUID(UUID genId) {
		Product selected = productRepo.findByGenId(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Product:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Product> getAll() {
		return productRepo.findAll();
	}

	@Override
	@Transactional
	public Product update(long id, Product productRequest) {
		Product selected = productRepo.getReferenceById(id);
		if (selected != null) {
			return updateObj(productRequest, selected);
		} else {
			throw new RuntimeException("Not found Product:" + String.valueOf(id));
		}
	}

	private Product updateObj(Product productRequest, Product selected) {
		selected.setProductCategoryId(productRequest.getProductCategoryId());
		selected.setCurrencyId(productRequest.getCurrencyId());
		selected.setName(productRequest.getName());
		selected.setPrice(productRequest.getPrice());
		selected.setSalePrice(productRequest.getSalePrice());
		selected.setBrandId(productRequest.getBrandId());
		selected.setUnitId(productRequest.getUnitId());
		Product updated = productRepo.save(selected);
		if (updated != null) {
			return updated;
		} else {
			throw new RuntimeException("Can't update Product:" + selected.getId());
		}
	}

	@Override
	@Transactional
	public boolean delete(long id) {
		Product selected = productRepo.getReferenceById(id);
		if (selected != null) {
			productRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Product updateByUUID(UUID genID, Product productRequest) {
		Product selected = productRepo.findByGenId(genID);
		if (selected != null) {
			return updateObj(productRequest, selected);
		} else {
			throw new RuntimeException("Not found Product:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public boolean deleteByUUID(UUID genID) {
		Product selected = productRepo.findByGenId(genID);
		if (selected != null) {
			productRepo.deleteById(selected.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<Product> getAll(int pageNo, int pageSize, String sortBy, String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Product> page = productRepo.findAll(pageable);
		return page;
	}

	@Override
	public Page<Product> findByProductCategoryId(long categoryId, int pageNo, int pageSize, String sortBy,
			String sortType) {
		Pageable pageable = genPageable(pageNo, pageSize, sortBy, sortType);
		Page<Product> page = productRepo.findByProductCategoryId(categoryId, pageable);
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
