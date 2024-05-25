package com.hongha.ver1.dtos;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseDTO {
	private Long id;
	private UUID genId;
	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;

}
