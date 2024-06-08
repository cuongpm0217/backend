package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.BranchListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(BranchListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_branch")
public class Branch extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;
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
