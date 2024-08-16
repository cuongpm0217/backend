package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@EntityListeners(ProductGroupListener.class)
@Table(name = "_product_group")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroup extends BaseEntityAudit {

	private static final long serialVersionUID = 4525858241774645250L;
	@Column
	private String name;
	@Column
	private String code;

}
