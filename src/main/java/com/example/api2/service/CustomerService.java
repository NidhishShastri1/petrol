package com.example.api2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api2.model.BlockedCard;
import com.example.api2.model.Customer;
import com.example.api2.repository.BlockedCardRepository;
import com.example.api2.repository.CustomerRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Random random = new Random();

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Autowired
    private BlockedCardRepository blockedCardRepository;

    

    // Method to check if a customer already exists based on customerName and mobileNumber
    public boolean customerExists(String customerName, String mobileNumber) {
        // Check if a customer with the same name and mobile number exists
        return customerRepository.findByCustomerNameAndMobileNumber(customerName, mobileNumber) != null;
    }

    // Method to fetch a customer by mobile number
    public Optional<Customer> getCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber);
    }

    // Method to add a new customer if they don't already exist
    public Customer addCustomer(String customerName, String mobileNumber) {
        // Check if the customer already exists
        if (customerExists(customerName, mobileNumber)) {
            // If the customer exists, return null or throw an exception as needed
            return null; // Or you can throw an exception like DuplicateCustomerException
        }
        
        
        

        // Generate Customer ID and Card Number
        String customerId = generateCustomerId();
        String cardNumber = generateCardNumber();

        // Create and save the customer
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setMobileNumber(mobileNumber);
        customer.setCustomerId(customerId);
        customer.setCardNumber(cardNumber);

        return customerRepository.save(customer);
    }

    private String generateCustomerId() {
        // Generate a 4-digit random number
        int randomNumber = 1000 + random.nextInt(9000); // Ensures a 4-digit number
        return "CUST" + randomNumber; // e.g., CUST1234
    }

    private String generateCardNumber() {
        // Generate a 4-digit random number for the card number
        int randomNumber = 1000 + random.nextInt(9000); // Ensures a 4-digit number
        return "CARD" + randomNumber; // e.g., CARD1234
    }

    public void blockCard(String cardNumber, Customer customer) {
        // Store the blocked card details in the `blocked_cards` collection
        BlockedCard blockedCard = new BlockedCard();
        blockedCard.setCustomerId(customer.getCustomerId());
        blockedCard.setName(customer.getCustomerName());
        blockedCard.setContactNumber(customer.getMobileNumber());
        blockedCard.setOldCardNumber(cardNumber);
        blockedCardRepository.save(blockedCard);

        // Remove the old card number from the `customers` collection
        customer.setCardNumber(null);
        customerRepository.save(customer);
    }
 // Generate a new card and update the customer record
    public Customer generateNewCard(Customer customer) {
    	String newCardNumber = "CARD" + (1000 + new Random().nextInt(9000));
        customer.setCardNumber(newCardNumber);
        return customerRepository.save(customer);
    }

	public Optional<Customer> getCustomerByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		return customerRepository.findByCardNumber(cardNumber);
	}
    

}
