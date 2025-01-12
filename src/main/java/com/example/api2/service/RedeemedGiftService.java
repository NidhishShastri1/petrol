package com.example.api2.service;



import com.example.api2.model.RedeemedGift;
import com.example.api2.repository.RedeemedGiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedeemedGiftService {

    private final RedeemedGiftRepository redeemedGiftRepository;

    public RedeemedGiftService(RedeemedGiftRepository redeemedGiftRepository) {
        this.redeemedGiftRepository = redeemedGiftRepository;
    }

    public List<RedeemedGift> getAllRedeemedGifts() {
        return redeemedGiftRepository.findAll();
    }

    // You can add custom query methods if needed
}

