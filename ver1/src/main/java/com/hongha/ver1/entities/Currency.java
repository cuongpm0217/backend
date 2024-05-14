package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_currency")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency extends BaseEntityAudit{
	@Column(nullable = false)
	private String name;//VND
	@Column
	private String fullName;//Việt Nam Đồng
	@Column
	private String symbol;//₫
	@Column(name="exchange_VND")
	private long exchangeVND;//vnd > 1 usd > 24500
}
