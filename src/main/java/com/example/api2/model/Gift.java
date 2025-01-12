package com.example.api2.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


	@Document(collection = "gift")
	public class Gift {
	    @Id
	    private String id;
	    private String itemName;
	    private int numberOfItems;
	    private LocalDate dateOfArrival;
	    private int pointsNeeded;
	    private int stock;

	    // Constructors, Getters, and Setters
	    public Gift(String itemName, int numberOfItems, LocalDate dateOfArrival, int pointsNeeded) {
	        this.itemName = itemName;
	        this.numberOfItems = numberOfItems;
	        this.dateOfArrival = dateOfArrival;
	        this.pointsNeeded = pointsNeeded;
	    }

	   

		



		public String getItemName() {
	        return itemName;
	    }

	    public void setItemName(String itemName) {
	        this.itemName = itemName;
	    }

	    public int getNumberOfItems() {
	        return numberOfItems;
	    }

	    public void setNumberOfItems(int numberOfItems) {
	        this.numberOfItems = numberOfItems;
	        this.stock = numberOfItems;
	    }

	    public LocalDate getDateOfArrival() {
	        return dateOfArrival;
	    }

	    public void setDateOfArrival(LocalDate dateOfArrival) {
	        this.dateOfArrival = dateOfArrival;
	    }
	    public int getPointsNeeded() {
	        return pointsNeeded;
	    }

	    public void setPointsNeeded(int pointsNeeded) {
	        this.pointsNeeded = pointsNeeded;
	    }
	    public int getStock() {
	        return stock;
	    }

	    public void setStock(int stock) {
	        this.stock = stock;
	    }
	    
	}


