package com.example.api2.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.api2.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}



