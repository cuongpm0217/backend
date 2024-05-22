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
@Table(name="_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntityAudit{
	@Column
	private String name;
	@Column
	private long userId;
	@Column
	private long positionId;
	@Column
	private long departmentId;
	@Column
	private long branchId;
	@Column
	private Date dob;
	@Column
	private String nationalID;///cmt cccd
	@Column
	private String phone1;
	@Column
	private String phone2;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private String avatar;//url image
	
}
