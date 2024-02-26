package com.microservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private Long customerId;
	private String name;
	private String email;
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name = "created_at")
	public LocalDateTime createdAt;
	@Column(name="created_by")
	public String createdBy;
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	@Column(name="updated_by")
	public String updatedBy;
	

}
