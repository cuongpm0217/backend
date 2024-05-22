package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_surrogate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Surrogate extends BaseEntityAudit{
	@Column(nullable = false)
	private String name;
	@Column
	private String phone;
	@Column
	private String address;
	@Column
	private String company;
}
