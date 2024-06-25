package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurrogateDTO extends BaseDTO {
	private String name;
	private boolean gender;
	private String phone;
	private String address;
	private String company;
	private long customerId;
	private int no;
	private String tittle;
}
