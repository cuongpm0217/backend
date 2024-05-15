package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO extends BaseDTO {
	private String name;
	private String address1;
	private String address2;
	private String phone1;
	private String phone2;
	private String bankAccountNo;
	private String bank;
}
