package com.microservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
	@NotEmpty(message = "account number cannot be empty")
	@Size(min = 14,max=14,message = "account no can be of 14 characters only")

	private Long accountNumber;
	@NotEmpty(message = "account type cannot be empty")

	private String accountType;
	@NotEmpty(message = "branch address cannot be empty")

	private String branchAddress;

}
