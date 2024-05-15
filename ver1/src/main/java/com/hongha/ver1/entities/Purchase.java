package com.hongha.ver1.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntityAudit{
	@Column
	private String code;
	@Column
	private String detail;
	@Column
	private Date dateReceipt;
	@Column
	private long partnerId;
	@Column
	private long employeeId;	
}
