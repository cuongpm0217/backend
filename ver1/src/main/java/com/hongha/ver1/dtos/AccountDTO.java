package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO extends BaseDTO {
	private String code;// 131 311..
	private String name;// phải thu của khách....
	private int level;// 1-131 2-1311 2-1312

}
