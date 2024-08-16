package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.ProducCategorytListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(ProducCategorytListener.class)
@Table(name = "_product_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String name;
	@Column
	private String code;
}
