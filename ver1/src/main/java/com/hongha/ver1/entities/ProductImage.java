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
public class ProductImage extends BaseEntityAudit{
	private static final long serialVersionUID = -8654890181139930993L;
	@Column(nullable = false,name="product_id")
	private long productId;
	@Column
	private String url;
	

}
