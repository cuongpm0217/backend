package com.hongha.ver1.entities;

import java.time.LocalDate;
import com.hongha.ver1.entities.listeners.EmployeeListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(EmployeeListener.class)
@Table(name = "_employee")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	@Column
	private String name;
	@Column(name = "user_id")
	private long userId;
	@Column(name = "position_id")
	private long positionId;
	@Column(name = "department_id")
	private long departmentId;
	@Column(name = "branch_id")
	private long branchId;
	@Column
	private LocalDate dob;
	@Column
	private boolean gender;
	@Column(name = "national_id")
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
