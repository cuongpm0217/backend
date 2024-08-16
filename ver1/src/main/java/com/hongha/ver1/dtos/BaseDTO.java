package com.hongha.ver1.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@MappedSuperclass
public abstract class BaseDTO {
	private Long id;
	private UUID genId;
	private String createdBy;
	private String updatedBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private long version;

}
