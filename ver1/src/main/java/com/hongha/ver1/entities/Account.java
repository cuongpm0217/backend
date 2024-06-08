package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.AccountListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AccountListener.class)
@Table(name = "_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;
	@Column
	private String code;// 131 311..
	@Column
	private String name;// phải thu của khách....
	@Column
	private int level;// 1-131 2-1311 2-1312

}
