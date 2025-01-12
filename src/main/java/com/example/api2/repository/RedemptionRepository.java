package com.example.api2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.api2.model.Redemption;

@Repository
public interface RedemptionRepository extends MongoRepository<Redemption, String> {
	
}