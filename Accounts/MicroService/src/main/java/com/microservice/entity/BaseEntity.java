package com.microservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BaseEntity {
	@Column(updatable = false,name = "created_at")
	public LocalDateTime createdAt;
	@Column(updatable = false,name="created_by")
	public String createdBy;
	@Column(insertable   = false,name="updated_at")
	private LocalDateTime updatedAt;
	@Column(insertable   = false,name="updated_by")
	public String updatedBy;

	

}
