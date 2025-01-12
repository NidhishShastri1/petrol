package com.example.api2.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api2.model.Customer;
import com.example.api2.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Customer> addCustomer(
            @RequestParam("customerName") String customerName,
            @RequestParam("mobileNumber") String mobileNumber) {
        Customer customer = customerService.addCustomer(customerName, mobileNumber);
        return ResponseEntity.ok(customer);
    }

}

// Request DTO
class CustomerRequest {
    private String customerName;
    private String mobileNumber;

    // Getters and Setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

