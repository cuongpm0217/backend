package com.hongha.ver1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntityAudit{
	@Column(nullable = false)
	private String name;
	@Column
	private String address="Giao Thủy, Nam Định";//set as default
	@Column
	private String phone1;
	@Column
	private String phone2;
	@Column(nullable = false)
	private long customerTypeId;
	@Column(name = "surrogate_id")
	private long surrogateId;
}
