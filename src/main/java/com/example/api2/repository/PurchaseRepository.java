package com.example.api2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.api2.model.PurchaseDetails;
@Repository
public interface PurchaseRepository extends MongoRepository<PurchaseDetails, String> {
}
