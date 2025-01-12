package com.example.api2.model;

public class CustomerReport {

    private String mobileNumber;
    private String customerId;
    private String cardNumber;
    private String customerName;
    private int points;
    private int giftsRedeemed;
    private int pointsConsumed;

    // Constructors, Getters, and Setters

    public CustomerReport(String mobileNumber, String customerId, String cardNumber,
            String customerName, Integer points, Integer giftsRedeemed, Integer pointsConsumed) {

    	this.mobileNumber = mobileNumber;
    	this.customerId = customerId;
    	this.cardNumber = cardNumber;
    	this.customerName = customerName;
    	this.points = points != null ? points : 0;  // Use default value 0 if null
    	this.giftsRedeemed = giftsRedeemed != null ? giftsRedeemed : 0;  // Default to 0
    	this.pointsConsumed = pointsConsumed != null ? pointsConsumed : 0;  // Default to 0
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGiftsRedeemed() {
        return giftsRedeemed;
    }

    public void setGiftsRedeemed(int giftsRedeemed) {
        this.giftsRedeemed = giftsRedeemed;
    }

    public int getPointsConsumed() {
        return pointsConsumed;
    }

    public void setPointsConsumed(int pointsConsumed) {
        this.pointsConsumed = pointsConsumed;
    }
}
