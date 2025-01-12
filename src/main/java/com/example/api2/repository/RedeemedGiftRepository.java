package com.example.api2.repository;



import com.example.api2.model.RedeemedGift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedeemedGiftRepository extends MongoRepository<RedeemedGift, String> {
    // You can add custom query methods if needed
}
