package com.hongha.ver1.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends BaseDTO{
	private String name;
	private UserDTO userDTO;	
	private PositionDTO positionDTO;
	private DepartmentDTO departmentDTO;
	private LocalDate DOB;
	private boolean gender;
	private String nationalID;///cmt cccd
	private String phone1;
	private String phone2;
	private String address1;
	private String address2;
	private String avatar;//url image
	private int no;
	private String tittle;
}
