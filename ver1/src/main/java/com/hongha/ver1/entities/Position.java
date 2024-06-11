package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.PositionListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(PositionListener.class)
@Table(name = "_position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position extends BaseEntityAudit{
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String name;
	@Column
	private String vname;
	@Column
	private int level = 9;
}
