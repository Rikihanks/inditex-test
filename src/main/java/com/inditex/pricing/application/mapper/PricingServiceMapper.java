package com.inditex.pricing.application.mapper;

import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.domain.model.Pricing;
import org.springframework.stereotype.Component;

@Component
public class PricingServiceMapper {

    public PricingDtoResponse fromDomain(Pricing pricing) {
        if (pricing == null) {
            return null;
        }
        return new PricingDtoResponse(
                pricing.getProductId(),
                pricing.getBrandId(),
                pricing.getPriceList(),
                pricing.getStartDate(),
                pricing.getEndDate(),
                pricing.getPrice()
        );
    }
}
