package com.microservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private Long customerId;
	@NotEmpty(message = "customerName cannot be empty")
	@Size(min=5,max=40,message = "the length of the name should be between 5 and 40 characters")
	private String name;
	@NotEmpty(message = "Email field cannot be empty")
	@Email(message = "email should be of valid format")
	private String email;
	@Pattern(regexp = "\\d{10}", message = "mobile Number Must be a 10" +
			"-digit number")
	@NotEmpty(message = "mobile no cant be null")

	private String mobileNumber;

	private AccountsDto accountsDto;
	

}
