package com.example.api2.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api2.model.Customer;
import com.example.api2.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Fetch Customer Details by Mobile Number (Existing)
    @PostMapping("/fetch")
    public ResponseEntity<Map<String, Object>> getCustomerDetails(
            @RequestParam String mobileNumber) {

        Map<String, Object> response = new HashMap<>();

        // Validate input
        if (mobileNumber == null || mobileNumber.length() != 10) {
            response.put("status", "error");
            response.put("message", "Valid mobile number is required.");
            return ResponseEntity.badRequest().body(response);
        }

        // Fetch customer from database
        Optional<Customer> customer = customerService.getCustomerByMobileNumber(mobileNumber);
        if (customer == null) {
            response.put("status", "error");
            response.put("message", "Customer not found.");
            return ResponseEntity.badRequest().body(response);
        }

        // Return customer details
        response.put("status", "success");
        response.put("customer", customer);
        return ResponseEntity.ok(response);
    }

    // Generate Customer Details (For Generating Random Customer ID and Card Number)
    @PostMapping("/generate-customer-details")
    public ResponseEntity<Map<String, String>> generateCustomerDetails(
            @RequestParam String customerName,
            @RequestParam String mobileNumber) {

        Map<String, String> response = new HashMap<>();

        // Validate inputs
        if (customerName == null || customerName.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Customer name is required.");
            return ResponseEntity.badRequest().body(response);
        }
        if (mobileNumber == null || mobileNumber.length() != 10) {
            response.put("status", "error");
            response.put("message", "Valid mobile number is required.");
            return ResponseEntity.badRequest().body(response);
        }

        // Generate customer ID and card number
        String customerId = "CUST" + (1000 + new Random().nextInt(9000));
        String cardNumber = "CARD" + (1000 + new Random().nextInt(9000));

        // Respond with generated details
        response.put("customerId", customerId);
        response.put("cardNumber", cardNumber);
        response.put("status", "success");

        return ResponseEntity.ok(response);
    }

    // Add New Customer (Save Customer to Database)
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addCustomer(
            @RequestParam String customerName,
            @RequestParam String mobileNumber) {

        Map<String, String> response = new HashMap<>();

        // Validate inputs
        if (customerName == null || customerName.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Customer name is required.");
            return ResponseEntity.badRequest().body(response);
        }
        if (mobileNumber == null || mobileNumber.length() != 10) {
            response.put("status", "error");
            response.put("message", "Valid mobile number is required.");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if the customer already exists in the database
        if (customerService.customerExists(customerName, mobileNumber)) {
            // Customer already exists, return error response
            response.put("status", "error");
            response.put("message", "User already exists.");
            return ResponseEntity.badRequest().body(response);  // Returning a 400 Bad Request
        }

        // Customer doesn't exist, proceed to save the customer
        Customer customer = customerService.addCustomer(customerName, mobileNumber);

        // Respond with customer details
        response.put("status", "success");
        response.put("message", "Customer saved successfully.");
        response.put("customerId", customer.getCustomerId());
        response.put("cardNumber", customer.getCardNumber());

        return ResponseEntity.ok(response);  // Returning a 200 OK with customer details
    }
  
     

    @PostMapping("/block-card")
    public ResponseEntity<?> blockCard(@RequestParam String cardNumber, @RequestParam String mobileNumber) {
        Optional<Customer> customerOpt = customerService.getCustomerByCardNumber(cardNumber);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customerService.blockCard(cardNumber, customer);
            return ResponseEntity.ok("Card blocked successfully.");
        }
        return ResponseEntity.notFound().build();
    }

        @PostMapping("/generate-new-card")
        public ResponseEntity<?> generateNewCard(@RequestParam String mobileNumber) {
            Optional<Customer> customerOpt = customerService.getCustomerByMobileNumber(mobileNumber);
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                Customer updatedCustomer = customerService.generateNewCard(customer);
                return ResponseEntity.ok(updatedCustomer);
            }
            return ResponseEntity.status(404).body("Customer not found.");
        }
    }

    

