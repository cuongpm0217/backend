package com.hongha.ver1.entities;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntityAudit{
	@Column(nullable = false)
	private String name;
	@Column(name="product_type_id",nullable = false)
	private long productTypeId;
	@Column(name="product_brand_id")
	private long productBrandId;
	@Column
	private BigInteger price;// saling price
	@Column(name="currency_id")
	private long currencyId;
	@Column(name="unit_id")
	private long unitId;
	
}
