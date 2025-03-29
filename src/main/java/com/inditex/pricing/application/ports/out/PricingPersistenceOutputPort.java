package com.inditex.pricing.application.ports.out;

import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Pricing;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricingPersistenceOutputPort {
    Optional<Pricing> findByBrandIdAndProductIdAndDate(LocalDateTime applicationDate, Long productId, Long brandId) throws PriceNotFoundException;
}
