package com.microservice.service;

import com.microservice.dto.CustomerDto;

public interface AccountsService {

    void createAccount(CustomerDto customerDto);
    CustomerDto findCustomerByMobileNo(String mobileNo);

    CustomerDto updateAccount(CustomerDto customerDto);
    void deleteAccount(Long customerId);
}
