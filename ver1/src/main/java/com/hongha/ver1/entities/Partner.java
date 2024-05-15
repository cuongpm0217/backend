package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_partner")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partner extends BaseEntityAudit{
	@Column
	private String name;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private String phone1;
	@Column
	private String phone2;
	@Column
	private String bankAccountNo;
	@Column
	private String bank;
}
