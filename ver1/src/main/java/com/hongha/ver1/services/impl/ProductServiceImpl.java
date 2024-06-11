package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Product;
import com.hongha.ver1.repositories.ProductRepository;
import com.hongha.ver1.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
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
		Product selected = productRepo.findByUUID(genId);
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
			selected.setCurrencyId(productRequest.getCurrencyId());
			selected.setName(productRequest.getName());
			selected.setPrice(productRequest.getPrice());
			selected.setProductBrandId(productRequest.getProductBrandId());
			selected.setProductTypeId(productRequest.getProductTypeId());
			selected.setUnitId(productRequest.getUnitId());
			Product updated = productRepo.save(selected);
			if(updated!=null) {
				return updated;
			}else {
				throw new RuntimeException("Can't update Product:"+String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Product:" + String.valueOf(id));
		}		
	}

	@Override
	@Transactional
	public void delete(long id) {
		Product selected = productRepo.getReferenceById(id);
		if(selected!=null) {
			productRepo.deleteById(id);
		}else {
			throw new RuntimeException("Not found Product:"+String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Product updateByUUID(UUID genID, Product productRequest) {
		Product selected = productRepo.findByUUID(genID);
		if (selected != null) {
			selected.setCurrencyId(productRequest.getCurrencyId());
			selected.setName(productRequest.getName());
			selected.setPrice(productRequest.getPrice());
			selected.setProductBrandId(productRequest.getProductBrandId());
			selected.setProductTypeId(productRequest.getProductTypeId());
			selected.setUnitId(productRequest.getUnitId());			
			Product updated = productRepo.save(selected);
			if(updated!=null) {
				return updated;
			}else {
				throw new RuntimeException("Can't update Product:"+String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Product:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Product selected = productRepo.findByUUID(genID);
		if(selected!=null) {
			productRepo.deleteById(selected.getId());
		}else {
			throw new RuntimeException("Not found Product:"+String.valueOf(genID));
		}
	}
}
