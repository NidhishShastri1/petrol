package com.example.api2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "redeemedgift")
public class RedeemedGift {

    @Id
    private String id;
    private String itemName;
    private String cardNumber;
    private String customerName;
    private Date dateOfRedemption;
    private int numberOfItemsRedeemed;
    private int pointsDeducted;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public Date getDateOfRedemption() {
        return dateOfRedemption;
    }

    public void setDateOfRedemption(Date dateOfRedemption) {
        this.dateOfRedemption = dateOfRedemption;
    }

    public int getNumberOfItemsRedeemed() {
        return numberOfItemsRedeemed;
    }

    public void setNumberOfItemsRedeemed(int numberOfItemsRedeemed) {
        this.numberOfItemsRedeemed = numberOfItemsRedeemed;
    }

    public int getPointsDeducted() {
        return pointsDeducted;
    }

    public void setPointsDeducted(int pointsDeducted) {
        this.pointsDeducted = pointsDeducted;
    }
}
