package com.hongha.ver1.entities;

import java.util.Date;

import com.hongha.ver1.entities.listeners.EmployeeListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(EmployeeListener.class)
@Table(name = "_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
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
	private String nationalID;/// cmt cccd
	@Column
	private String phone1;
	@Column
	private String phone2;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private String avatar;// url image

}
