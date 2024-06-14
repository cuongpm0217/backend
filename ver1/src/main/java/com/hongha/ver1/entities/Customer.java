package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.CustomerListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(CustomerListener.class)
@Table(name="_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntityAudit{
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String name;
	@Column
	private String address1="Giao Thủy, Nam Định";//set as default
	@Column
	private String address2;
	@Column
	private String phone1;
	@Column
	private String phone2;
	@Column(nullable = false,name="customer_type_id")
	private long customerTypeId;
	
}
