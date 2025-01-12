package com.example.api2.service;


import org.springframework.stereotype.Service;

import com.example.api2.model.Customer;
import com.example.api2.repository.CustomerRepository;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(String customerName, String mobileNumber) {
        // Generate Customer ID and Card Number
        String customerId = "CUST" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        String cardNumber = "CARD" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        // Create and save the customer
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setMobileNumber(mobileNumber);
        customer.setCustomerId(customerId);
        customer.setCardNumber(cardNumber);

        return customerRepository.save(customer);
    }
}


