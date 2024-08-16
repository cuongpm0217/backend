package com.hongha.ver1.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends BaseDTO {
	private String name;
	private long userId;
	private long positionId;
	private long departmentId;
	private long branchId;
	private Date dob;
	private boolean gender;
	private String nationalID;/// cmt cccd
	private String phone1;
	private String phone2;
	private String address1;
	private String address2;
	private String avatar;// url image
	private int no;
	private String tittle;
}
