package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.ProductListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(ProductListener.class)
@Table(name = "_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;
	@Column(name = "category_id")
	private long productCategoryId;
	@Column(name = "brand_id")
	private long brandId;
	@Column
	private double price;// saling price
	@Column(name = "sale_price")
	private double salePrice;
	@Column(name = "currency_id")
	private long currencyId;
	@Column(name = "unit_id")
	private long unitId;
	@Column
	private String country;
}
