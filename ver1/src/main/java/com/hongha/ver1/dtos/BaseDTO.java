package com.hongha.ver1.dtos;

import java.util.Date;
import java.util.UUID;

<<<<<<< HEAD
=======
import com.hongha.ver1.utils.Convertor;

>>>>>>> a833e23cca83a037a87c92ee39e57e07f3c32774
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
