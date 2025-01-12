package com.example.api2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api2.model.Gift;
import com.example.api2.model.PurchaseDetails;
import com.example.api2.repository.GiftRepository;
import com.example.api2.repository.PurchaseRepository;

@Service
public class GiftService {

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    // Service method to add the gift to the gift_in collection
    public void addGiftIn(Gift gift) {
        if (gift == null || gift.getItemName() == null) {
            throw new IllegalArgumentException("Gift or gift item name cannot be null.");
        }
        synchronizeStock(gift); // Ensure stock is synchronized with numberOfItems
        giftRepository.save(gift);
    }

    // Fetch all available gifts in stock
    public List<Gift> getAllGiftStock() {
        return giftRepository.findAll();
    }

    // Get list of eligible gifts for a customer
    public List<Map<String, Object>> getEligibleGifts(String customerId, int customerPoints) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty.");
        }
        List<Gift> gifts = giftRepository.findAll();
        List<Map<String, Object>> eligibleGifts = new ArrayList<>();

        for (Gift gift : gifts) {
            synchronizeStock(gift); // Ensure stock is up to date
            Map<String, Object> giftDetails = new HashMap<>();
            giftDetails.put("itemName", gift.getItemName());
            giftDetails.put("pointsNeeded", gift.getPointsNeeded());

            if (gift.getStock() <= 0) {
                giftDetails.put("eligibility", "Out of Stock");
            } else if (customerPoints >= gift.getPointsNeeded()) {
                giftDetails.put("eligibility", "Buy");
            } else {
                giftDetails.put("eligibility", "Not Enough Points");
            }

            eligibleGifts.add(giftDetails);
        }

        return eligibleGifts;
    }

    // Handle purchasing a gift
    public void purchaseGift(PurchaseDetails purchaseDetails) {
        if (purchaseDetails == null || purchaseDetails.getGiftName() == null) {
            throw new IllegalArgumentException("Purchase details or gift name cannot be null.");
        }

        // Reduce gift stock
        Gift gift = giftRepository.findByitemName(purchaseDetails.getGiftName());
        if (gift == null) {
            throw new RuntimeException("Gift not found: " + purchaseDetails.getGiftName());
        }

        synchronizeStock(gift); // Ensure stock is up to date

        if (gift.getStock() <= 0) {
            throw new RuntimeException("Gift out of stock: " + gift.getItemName());
        }

        if (purchaseDetails.getPointsSpent() < gift.getPointsNeeded()) {
            throw new RuntimeException("Insufficient points for gift: " + gift.getItemName());
        }

        // Deduct stock and save
        gift.setNumberOfItems(gift.getNumberOfItems() - 1); // Update numberOfItems
        synchronizeStock(gift); // Ensure stock reflects the change
        giftRepository.save(gift);

        // Save purchase details
        purchaseRepository.save(purchaseDetails);
    }

    // Update points needed for a gift
    public void setGiftPoints(String itemName, int pointsRequired) {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Gift item name cannot be null or empty.");
        }

        Gift gift = giftRepository.findByitemName(itemName);
        if (gift == null) {
            throw new RuntimeException("Gift not found: " + itemName);
        }

        gift.setPointsNeeded(pointsRequired); // Update points required
        synchronizeStock(gift); // Ensure stock is up to date
        giftRepository.save(gift);
    }

    // Utility method to ensure stock matches numberOfItems
    private void synchronizeStock(Gift gift) {
        if (gift != null) {
            gift.setStock(gift.getNumberOfItems()); // Synchronize stock with numberOfItems
        }
    }
}
