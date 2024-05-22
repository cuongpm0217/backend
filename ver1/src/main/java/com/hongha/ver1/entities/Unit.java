package com.hongha.ver1.entities;


import com.hongha.ver1.entities.enums.EUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_unit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit extends BaseEntityAudit {
	@Enumerated(EnumType.STRING)
	@Column(unique = true,nullable = false)
	private EUnit name;// cái, bộ, công
}
