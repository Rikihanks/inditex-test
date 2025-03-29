package com.inditex.pricing.application.ports.in;

import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public interface GetPricingInputPort {

    PricingDtoResponse getPricing(LocalDateTime applicationDate, Long productId, Long brandId) throws PriceNotFoundException;
}
