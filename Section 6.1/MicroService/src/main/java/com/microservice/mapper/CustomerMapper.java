package com.microservice.mapper;

import com.microservice.dto.AccountsDto;
import com.microservice.dto.CustomerDto;
import com.microservice.entity.Accounts;
import com.microservice.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomersDto(Customer customer, CustomerDto customerDto)
    {
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setCustomerId(customer.getCustomerId());
        return customerDto;
    }

    public static Customer mapToCustomers(Customer customer, CustomerDto customerDto)
    {
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setCustomerId(customerDto.getCustomerId());

        return customer;
    }
}
