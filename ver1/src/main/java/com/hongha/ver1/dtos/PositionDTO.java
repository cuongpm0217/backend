package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO extends BaseDTO {
	private String name;

	private int level = 8;// 1 tgd 2 ptgd 3 gd 4 pgd 5 tp 6 pp 7 t nhom 8 nv
}
