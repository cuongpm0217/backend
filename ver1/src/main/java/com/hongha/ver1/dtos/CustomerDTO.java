package com.hongha.ver1.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends BaseDTO{
	private String name;
	private String address="Giao Thủy, Nam Định";//set as default
	private String phone1;
	private String phone2;
	@Column(nullable = false)
	private long customerTypeId;
	@Column(name = "surrogate_id")
	private long surrogateId;// dai dien
}
