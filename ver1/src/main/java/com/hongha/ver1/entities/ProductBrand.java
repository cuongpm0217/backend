package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_product_brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductBrand extends BaseEntityAudit{
	@Column(nullable = false)
	private String name;
	@Column
	private String country;
}
