package com.hongha.ver1.entities;

import java.math.BigInteger;

import com.hongha.ver1.entities.enums.EUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_unit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit extends BaseEntityAudit {

	private EUnit name;// cái, bộ, công
}
