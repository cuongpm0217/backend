package com.hongha.ver1.entities;


import com.hongha.ver1.entities.enums.ECustomerType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_customer_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerType extends BaseEntityAudit{
	@Enumerated(EnumType.STRING)
	@Column(unique = true,nullable = false)
	private ECustomerType name;
}
