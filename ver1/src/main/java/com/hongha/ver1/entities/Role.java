package com.hongha.ver1.entities;

import com.hongha.ver1.entities.enums.ERole;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_role")
public class Role extends BaseEntityAudit {
	@Enumerated(EnumType.STRING)
	@Column(name = "name", unique = true)
	private ERole name = ERole.ROLE_USER;// set as default

}
