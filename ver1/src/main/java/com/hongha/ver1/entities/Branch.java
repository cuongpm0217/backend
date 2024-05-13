package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="_branch")
public class Branch extends BaseEntityAudit{
	@Column
	private String name;
	@Column
	private int level;
	@Column 
	private String address;
	@Column
	private String phone1;
	@Column
	private String phone2;
}
