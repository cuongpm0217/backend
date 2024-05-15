package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_proposal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proposal extends BaseEntityAudit{
	@Column
	private long customerId;
	@Column
	private String liencePlate;
	@Column
	private String vehicle;
}
