package com.inditex.pricing.application.mapper;

import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.domain.model.Pricing;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PricingServiceMapperTest {

    private final PricingServiceMapper mapper = new PricingServiceMapper();

    @Test
    void testFromDomain_HappyPath() {
        Pricing pricing = Pricing.builder()
                .brandId(200L)
                .productId(100L)
                .priceList(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .price(29.99)
                .build();

        PricingDtoResponse dtoResponse = mapper.fromDomain(pricing);

        assertEquals(pricing.getBrandId(), dtoResponse.brandId());
        assertEquals(pricing.getProductId(), dtoResponse.productId());
        assertEquals(pricing.getPriceList(), dtoResponse.priceList());
        assertEquals(pricing.getStartDate(), dtoResponse.startDate());
        assertEquals(pricing.getEndDate(), dtoResponse.endDate());
        assertEquals(pricing.getPrice(), dtoResponse.price());
    }

    @Test
    void testFromDomain_UnhappyPath() {
        Pricing pricing = null;

        PricingDtoResponse dtoResponse = mapper.fromDomain(pricing);

        assertNull(dtoResponse);
    }
}