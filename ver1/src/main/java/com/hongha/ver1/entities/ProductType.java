package com.hongha.ver1.entities;

import com.hongha.ver1.entities.enums.EProductType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="_product_Type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductType extends BaseEntityAudit{
	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false)
	private EProductType name = EProductType.EProductType_Product;//set as default
	
}
