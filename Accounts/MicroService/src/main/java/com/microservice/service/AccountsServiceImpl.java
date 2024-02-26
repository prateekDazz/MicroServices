package com.microservice.service;

import com.microservice.constants.AccountsConstants;
import com.microservice.dto.AccountsDto;
import com.microservice.dto.CustomerDto;
import com.microservice.entity.Accounts;
import com.microservice.entity.Customer;
import com.microservice.exception.CustomerAlreadyExistsException;
import com.microservice.exception.ResourceNotFoundException;
import com.microservice.mapper.AccountsMapper;
import com.microservice.mapper.CustomerMapper;
import com.microservice.repository.AccountsRepository;
import com.microservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements  AccountsService{
   @Autowired
    public AccountsRepository accountsRepository;
   @Autowired
    public CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
Customer customer = CustomerMapper.mapToCustomers(new Customer(),customerDto);


        Optional<Customer>existingCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(existingCustomer.isPresent())
        {
            throw new CustomerAlreadyExistsException("Customer with phone no"+ customerDto.getMobileNumber()+"already Exists");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("SYS");
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setUpdatedBy("SYS");
Customer savedCustomer = customerRepository.save(customer);
Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto findCustomerByMobileNo(String mobileNo) {
        Optional<Customer>existcustom =  customerRepository.findByMobileNumber(mobileNo);
        if(!existcustom.isPresent())
        {
            throw new ResourceNotFoundException("customer","mobileNumber",mobileNo);
        }
        CustomerDto customerDto = CustomerMapper.mapToCustomersDto(existcustom.get(),new CustomerDto());
        Accounts accounts = accountsRepository.findByCustomerId(existcustom.get().getCustomerId());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts,new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    @Override
    public CustomerDto updateAccount(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.getAccountsDto();
        Customer customer = CustomerMapper.mapToCustomers(new Customer(),customerDto);
        Accounts accounts = AccountsMapper.mapToAccounts(new Accounts(),accountsDto);
//        accounts.setCustomerId(customer.getCustomerId());
        Optional<Customer> savedCustomer =  customerRepository.findById(customer.getCustomerId());
        Optional<Accounts>savedAc =  accountsRepository.findById(accountsDto.getAccountNumber());
        if(!savedCustomer.isPresent())
        {
            throw new ResourceNotFoundException("customer","id",customer.getCustomerId().toString());
        }
        if(!savedAc.isPresent())
        {
            throw new ResourceNotFoundException("Account","Account Number",accountsDto.getAccountNumber().toString());

        }
        Customer updtCustomer = savedCustomer.get();
        Accounts updtAccount = savedAc.get();
        updtCustomer.setMobileNumber(customer.getMobileNumber());
        updtCustomer.setEmail(customer.getEmail());
        updtAccount.setAccountType(accounts.getAccountType());
        updtAccount.setBranchAddress(accounts.getBranchAddress());
        customerRepository.save(updtCustomer);
        accountsRepository.save(updtAccount);
        return customerDto;

    }

    @Override
    public void deleteAccount(Long customerId) {
        Optional<Customer>customer =customerRepository.findById(customerId);
        if(!customer.isPresent())
        {
            throw new ResourceNotFoundException("customer","id",customerId.toString());

        }
        customerRepository.delete(customer.get());
        Accounts accounts = accountsRepository.findByCustomerId(customerId);
        if(accounts ==null)
        {
            throw new ResourceNotFoundException("account","accountNumber",accounts.getAccountNumber().toString());

        }
        accountsRepository.delete(accounts);
    }

    private Accounts createNewAccount(Customer savedCustomer) {
Accounts account = new Accounts();
account.setCustomerId(savedCustomer.getCustomerId());
account.setAccountType(AccountsConstants.SAVINGS);
account.setBranchAddress(AccountsConstants.ADDRESS);
        Random random = new Random();

        // Generating a random long
        long randomLong = random.nextLong();
    account.setAccountNumber(randomLong);
    Accounts savedAccount = accountsRepository.save(account);
    return savedAccount;
    }
}
