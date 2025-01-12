package com.example.api2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api2.model.Customer;
import com.example.api2.model.CustomerReport;
import com.example.api2.model.Gift;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    // Custom query to find a customer by customerName and mobileNumber
    Customer findByCustomerNameAndMobileNumber(String customerName, String mobileNumber);

    // New custom query to find a customer by phone number
    Optional<Customer> findByMobileNumber(String mobileNumber);

    // Fetch customer data for report generation with required fields
    @Query("{ }")  // This fetches all customers. You can refine it as needed.
    List<CustomerReport> findAllCustomerReports();

    Optional<Customer> findBycustomerId(String customerId);
    Optional<Customer> findByCardNumber(String cardNumber);
}


