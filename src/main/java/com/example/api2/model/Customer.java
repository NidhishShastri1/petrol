package com.example.api2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "customers")
public class Customer {

    @Id
    private String id; // MongoDB automatically assigns an ID
    private String customerName;
    private String mobileNumber;
    private String customerId; // Generated manually
    private String cardNumber; // Generated manually
    private int points;

    // A new field to keep track of purchased gifts
    private List<String> purchasedGifts; 

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getPurchasedGifts() {
        return purchasedGifts;
    }

    public void setPurchasedGifts(List<String> purchasedGifts) {
        this.purchasedGifts = purchasedGifts;
    }
}
