package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO extends BaseDTO {
	private String name;
	private String vname;
	private long branchId;
	private String branchName;
	private String code;
	private int no;
	private String style;
//
}
