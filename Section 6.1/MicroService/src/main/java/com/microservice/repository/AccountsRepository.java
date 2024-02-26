package com.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.entity.Accounts;
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    public Accounts findByCustomerId(Long customerId);

}
