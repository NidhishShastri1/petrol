package com.example.api2.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api2.model.PurchaseDetails;
import com.example.api2.model.Redemption;
import com.example.api2.service.GiftService;
import com.example.api2.service.RedemptionService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/redemption")
public class RedemptionController {

    @Autowired
    private GiftService giftService;

    private final RedemptionService redemptionService;

    public RedemptionController(RedemptionService redemptionService) {
        this.redemptionService = redemptionService;
    }
    // API to fetch eligible gifts
    @GetMapping("/eligible-gifts")
    public ResponseEntity<List<Map<String, Object>>> getEligibleGifts(
            @RequestParam String customerId, 
            @RequestParam int customerPoints) {
        try {
            List<Map<String, Object>> eligibleGifts = giftService.getEligibleGifts(customerId, customerPoints);
            return ResponseEntity.ok(eligibleGifts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

 
        @Autowired
        private GiftService GiftService;


        // API to buy a gift
        @PostMapping("/buy")
        public ResponseEntity<?> buyGift(@RequestBody Redemption redemption) {
            try {
                // Fetch the points needed for the gift from the database
                int pointsNeeded = giftService.getPointsNeededForGift(redemption.getPointsUsed());

                // Call the service layer to process the redemption
                redemptionService.redeemGift(redemption.getCustomerId(), redemption.getGiftName(), pointsNeeded);
                return ResponseEntity.ok("Gift redeemed successfully.");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error: " + e.getMessage());
            }
        }
}





