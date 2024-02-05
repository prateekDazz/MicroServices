package com.microservice.controller;

import com.microservice.constants.AccountsConstants;
import com.microservice.dto.AccountsDto;
import com.microservice.dto.CustomerDto;
import com.microservice.dto.ErrorResponseDto;
import com.microservice.dto.ResponseDto;
import com.microservice.entity.Accounts;
import com.microservice.entity.Customer;
import com.microservice.mapper.AccountsMapper;
import com.microservice.mapper.CustomerMapper;
import com.microservice.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
    @Autowired
    private AccountsService accountsService;

    @Operation(
            summary = "create Account Details Rest API",
            description = "Rest API to create Customer details By CustomerDto"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HttpStatus OK"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount( @Valid @RequestBody CustomerDto customerDto)
    {
        accountsService.createAccount(customerDto);

return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "fetch Account Details Rest API",
            description = "Rest API to fetch Customer details By Mobile Number"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HttpStatus OK"
    )
    @GetMapping("/fetchAccount")
    public ResponseEntity<CustomerDto>fetchCustomerByMobileNumber(@RequestParam("mobile") @Pattern(regexp = "\\d{10}", message = "Must be a 10-digit number") String mobile)
    {
CustomerDto  customerDto = accountsService.findCustomerByMobileNo(mobile);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);

    }
    @Operation(
            summary = "Update Account Details Rest API",
            description = "Rest API to update account details"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HttpStatus OK"
    )
    @PutMapping("/updateAccount")
    public ResponseEntity<CustomerDto>updateAccount(@Valid @RequestBody CustomerDto customerDto)
    {
        CustomerDto customm = accountsService.updateAccount(customerDto);
        return  ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @Operation(
            summary = "Delete Account Details Rest API",
            description = "Rest API to delete account details"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HttpStatus OK"
    )
    @DeleteMapping("/deleteCustomer/{customerId}")

    public ResponseEntity<String>deleteAccount(@PathVariable("customerId")Long customerId)
    {
        accountsService.deleteAccount(customerId);
        return ResponseEntity.status(HttpStatus.OK).body("The Customer with Id"+customerId+"has been deleted ");
    }


}
