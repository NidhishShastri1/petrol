package com.example.api2.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api2.model.Gift;
import com.example.api2.service.GiftService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/gifts")
public class GiftController {
	private static final Logger logger = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private GiftService giftService;

    // API to add a new gift (handles multipart/form-data)
    @PostMapping(value = "/in", consumes = "multipart/form-data")
    public ResponseEntity<String> addGiftIn(
            @RequestParam String itemName,
            @RequestParam int numberOfItems,
            @RequestParam String dateOfArrival,
            @RequestParam int pointsNeeded) {
        try {
            // Convert the form-data into a GiftIn object
            Gift gift = new Gift(itemName, numberOfItems, LocalDate.parse(dateOfArrival), pointsNeeded);

            // Save the gift to the inventory
            giftService.addGiftIn(gift);

            return ResponseEntity.status(HttpStatus.CREATED).body("Gift added to inventory.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding gift: " + e.getMessage());
        }
    }

    // API to fetch gift stock
    @GetMapping("/stock")
    public ResponseEntity<?> getGiftStock() {
        logger.info("Fetching gift stock...");
        List<Gift> stock = giftService.getAllGiftStock();
        
        if (stock.isEmpty()) {
            logger.warn("No gift stock found!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        logger.info("Gift stock retrieved successfully.");
        return ResponseEntity.ok(stock);
    }
    

    // API to define points needed to redeem a gift
    @PostMapping(value = "/set-points", consumes = "multipart/form-data")
    public ResponseEntity<String> setGiftPoints(
            @RequestParam String itemName,
            @RequestParam int pointsNeeded) {
        try {
            // Call the service to update points required for the gift
            giftService.setGiftPoints(itemName, pointsNeeded);

            return ResponseEntity.ok("Points required to redeem the gift have been set.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error setting gift points: " + e.getMessage());
        }
    }
}
