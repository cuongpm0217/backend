package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseEntityAudit {
	@Column
	private String name;
	@Column
	private long branchId;
	@Column
	private String code;
}
