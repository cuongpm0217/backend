package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position extends BaseEntityAudit{
	@Column(nullable = false)
	private String name;
	@Column
	private int level = 8;// 1 tgd 2 ptgd 3 gd 4 pgd 5 tp 6 pp 7 t nhom 8 nv
}
