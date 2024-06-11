package com.hongha.ver1.entities;

import java.math.BigInteger;

import com.hongha.ver1.entities.enums.EExpenseType;
import com.hongha.ver1.entities.listeners.ExpenseListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(ExpenseListener.class)
@Table(name = "_expense")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EExpenseType name;
	@Column()
	private String detail;
	@Column
	private BigInteger cost;
}
