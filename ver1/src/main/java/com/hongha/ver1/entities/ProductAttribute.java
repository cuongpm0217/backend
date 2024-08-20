package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@EntityListeners(ProductListener.class)
@Table(name = "_product_attribute")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttribute extends BaseEntityAudit {
	private static final long serialVersionUID = -6711664354802113214L;
	@Column(nullable = false, name = "product_id")
	private long productId;
	@Column(nullable = false, name = "atb_name")
	private String atbName;
	@Column(name = "atb_value")
	private String atbValue;
}
