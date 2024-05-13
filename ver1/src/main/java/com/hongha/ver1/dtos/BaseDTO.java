package com.hongha.ver1.dtos;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseDTO {
	private Long id;
	private UUID gen_id;
	private String createBy;
	private String updateBy;
	private Date createdAt;
	private Date updatedAt;
	
}
