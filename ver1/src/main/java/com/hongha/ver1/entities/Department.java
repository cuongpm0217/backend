package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.DepartmentListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(DepartmentListener.class)
@Table(name = "_department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;
	@Column
	private String name;
	@Column
	private long branchId;
	@Column
	private String code;
}
