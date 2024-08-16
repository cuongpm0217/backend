package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.VehicleTypeListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(VehicleTypeListener.class)
@Table(name = "_vehicle_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleType extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;
	private String name;
}
