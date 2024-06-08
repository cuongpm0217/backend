package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.CurrencyListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(CurrencyListener.class)
@Table(name="_currency")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency extends BaseEntityAudit{
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String name;//VND
	@Column
	private String fullName;//Việt Nam Đồng
	@Column
	private String symbol;//₫
	@Column(name="exchange_VND")
	private long exchangeVND;//vnd > 1 usd > 24500
}
