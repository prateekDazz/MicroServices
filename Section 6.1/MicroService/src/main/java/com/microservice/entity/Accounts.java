package com.microservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Accounts extends BaseEntity {
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="account_number")
	@Id
	private Long accountNumber;
	@Column(name="account_type")
	private String accountType;
	@Column(name="branch_address")
	private String branchAddress;
	@Column(name = "created_at")
	public LocalDateTime createdAt;
	@Column(name="created_by")
	public String createdBy;
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	@Column(name="updated_by")
	public String updatedBy;
}
