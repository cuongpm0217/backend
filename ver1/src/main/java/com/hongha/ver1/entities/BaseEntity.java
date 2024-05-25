package com.hongha.ver1.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
//	uuid
//	@ID
//	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "gen_id",unique = true,nullable = false)
	private UUID genId = UUID.randomUUID();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BaseEntity))
			return false;
		BaseEntity that = (BaseEntity) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "BaseEntity {" + "id = " + id + "}";
	}
}
