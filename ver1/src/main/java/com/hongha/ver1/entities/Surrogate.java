package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.SurrogateListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(SurrogateListener.class)
@Table(name="_surrogate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Surrogate extends BaseEntityAudit{
	
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String name;
	@Column
	private boolean gender=true;//default man
	@Column
	private String phone;
	@Column
	private String address;
	@Column
	private String company;
	@Column(name="customer_id",nullable = false)
	private long customerId;
}
