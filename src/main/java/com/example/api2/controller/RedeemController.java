package com.example.api2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api2.model.Customer;
import com.example.api2.repository.CustomerRepository;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RedeemController {

    @Autowired
    private CustomerRepository customerRepository;




    /**
     * Retrieve all customers with their points information.
     *
     * @return List of all customers
     */
    @GetMapping("/api/points")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Endpoint to purchase a gift for a customer.
     *
     * @param customerId ID of the customer
     * @param giftId     ID of the gift to be purchased
     * @return Response with the purchase result
     */
    
    @PostMapping(value = "/api/add-points")
    public ResponseEntity<?> addPoints(
        @RequestParam("customerId") String customerId,
        @RequestParam("points") int points
    ) {
        System.out.println("Received customerId: " + customerId);

        // Find the customer by ID
        Optional<Customer> customerOpt = customerRepository.findBycustomerId(customerId);
        if (!customerOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }

        Customer customer = customerOpt.get();
        customer.setPoints(customer.getPoints() + points); // Add points to the customer's account
        customerRepository.save(customer); // Save the updated customer

        return ResponseEntity.ok(customer); // Return the updated customer object
    }

   
}
