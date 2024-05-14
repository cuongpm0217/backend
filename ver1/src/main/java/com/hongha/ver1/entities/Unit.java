package com.hongha.ver1.entities;

import java.math.BigInteger;

<<<<<<< HEAD
import com.hongha.ver1.entities.enums.EUnit;

=======
>>>>>>> a833e23cca83a037a87c92ee39e57e07f3c32774
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_unit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit extends BaseEntityAudit{
	
	private EUnit name;// cái, bộ, công
}
