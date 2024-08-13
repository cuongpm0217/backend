package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@EntityListeners(ProductGroupItemListener.class)
@Table(name = "_product_group_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	@Column(nullable = false,name="product_group_id")
	private long productGroupId;
	@Column(nullable = false,name="product_id")
	private long productId;

}
