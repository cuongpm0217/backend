package com.hongha.ver1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO extends BaseDTO {
	private String licensePlate;
	private String model;
	private String vehicleType;
	private int yearOfFactory;
}
