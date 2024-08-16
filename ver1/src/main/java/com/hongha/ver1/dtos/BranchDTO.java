package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO extends BaseDTO {
	private String name;
	private int level;
	private String address;
	private String phone1;
	private String phone2;
	private int no;
}
