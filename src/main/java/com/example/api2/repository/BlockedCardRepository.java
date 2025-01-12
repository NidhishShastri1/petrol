package com.example.api2.repository;

import com.example.api2.model.BlockedCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockedCardRepository extends MongoRepository<BlockedCard, String> {
}
