package com.hongha.ver1.dtos;

import java.util.Date;
import java.util.List;

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
public class CustomerDTO extends BaseDTO{
	private String name;
	private Date dob;
	private Boolean gender;
	private String address1;
	private String address2;
	private String phone1;
	private String phone2;	
	private CustomerTypeDTO customerTypeDTO;
	private List<SurrogateDTO> listSurroggate;
	private int no;
	private String tittle;
}
