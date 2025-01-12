package com.example.api2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "redemptions")
public class Redemption {

    @Id
    private String id;

    private String customerId;
    private String giftName;
    private int pointsUsed;
    private Date redemptionDate;
    private int numberOfItemsRedeemed;
    private String cardNumber;

    // Default constructor
    public Redemption() {}

    // Parameterized constructor
    public Redemption(String customerId, String giftName, int pointsUsed, Date redemptionDate, int numberOfItemsRedeemed, String cardNumber) {
        this.customerId = customerId;
        this.giftName = giftName;
        this.pointsUsed = pointsUsed;
        this.redemptionDate = redemptionDate;
        this.numberOfItemsRedeemed = numberOfItemsRedeemed;
        this.cardNumber = cardNumber;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public int getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(int pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public Date getRedemptionDate() {
        return redemptionDate;
    }

    public void setRedemptionDate(Date redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    public int getNumberOfItemsRedeemed() {
        return numberOfItemsRedeemed;
    }

    public void setNumberOfItemsRedeemed(int numberOfItemsRedeemed) {
        this.numberOfItemsRedeemed = numberOfItemsRedeemed;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Override toString method for better readability
    @Override
    public String toString() {
        return "Redemption{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", giftName='" + giftName + '\'' +
                ", pointsUsed=" + pointsUsed +
                ", redemptionDate=" + redemptionDate +
                ", numberOfItemsRedeemed=" + numberOfItemsRedeemed +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}

