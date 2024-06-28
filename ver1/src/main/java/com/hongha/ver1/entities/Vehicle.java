package com.hongha.ver1.entities;

import com.hongha.ver1.entities.listeners.VehicleListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(VehicleListener.class)
@Table(name="_vehicle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle extends BaseEntityAudit {
	
	private static final long serialVersionUID = 1L;
	@Column(name="license_plate")
	private String licensePlate;
	@Column
	private String model;
	@Column(name="vehicle_type_id")
	private long vehicleTypeId;
	@Column(name="yearOfFactory")
	private int yearOfFactory;
}
