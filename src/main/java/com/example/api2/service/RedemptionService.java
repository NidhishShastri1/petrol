package com.example.api2.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api2.model.Customer;
import com.example.api2.model.Redemption;
import com.example.api2.repository.CustomerRepository;
import com.example.api2.repository.RedemptionRepository;

@Service
public class RedemptionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RedemptionRepository redemptionRepository;

    public void redeemGift(String customerId, String giftName, int pointsNeeded) {
        // Fetch the customer from the database
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Check if the customer has enough points
        if (customer.getPoints() < pointsNeeded) {
            throw new RuntimeException("Not enough points to redeem the gift.");
        }

        // Deduct the points and save the customer back to the database
        customer.setPoints(customer.getPoints() - pointsNeeded);
        customerRepository.save(customer);

        // Save the redemption details in the redemption collection
        Redemption redemption = new Redemption();
        redemption.setCustomerId(customerId);
        redemption.setGiftName(giftName);
        redemption.setPointsUsed(pointsNeeded);
        redemption.setRedemptionDate(new Date());

        redemptionRepository.save(redemption);
    }
}

