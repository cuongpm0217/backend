package com.hongha.ver1.entities;

import com.hongha.ver1.entities.enums.ERole;
import com.hongha.ver1.entities.listeners.RoleListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(RoleListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_role")
public class Role extends BaseEntityAudit {
	
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	@Column(name = "name", unique = true)
	private ERole name = ERole.ROLE_USER;// set as default

}
