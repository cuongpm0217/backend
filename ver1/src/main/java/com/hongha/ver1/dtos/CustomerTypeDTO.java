package com.hongha.ver1.dtos;

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
public class CustomerTypeDTO extends BaseDTO {
	private String name;
	private int no;
	private List<CustomerDTO> listCustomerDTO;
}
