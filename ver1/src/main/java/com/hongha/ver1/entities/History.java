package com.hongha.ver1.entities;

import com.hongha.ver1.entities.enums.EAction;

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
@Table(name = "_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History extends BaseEntityAudit{
	@Column(updatable = false,nullable = false)
	private String obj;
	@Enumerated(EnumType.STRING)
	@Column(updatable = false,nullable = false)
	private EAction action;
	@Column(updatable = false)
	private String objId;	
}
