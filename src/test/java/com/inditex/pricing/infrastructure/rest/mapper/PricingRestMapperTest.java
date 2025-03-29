package com.inditex.pricing.infrastructure.rest.mapper;

import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.infrastructure.rest.dto.GetPricingRestResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PricingRestMapperTest {

    private final PricingRestMapper mapper = new PricingRestMapper();

    @Test
    void testToRestResponse() {
        PricingDtoResponse dtoResponse = new PricingDtoResponse(
                100L,
                200L,
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                29.99
        );

        GetPricingRestResponse restResponse = mapper.toRestResponse(dtoResponse);

        assertEquals(dtoResponse.productId(), restResponse.productId());
        assertEquals(dtoResponse.brandId(), restResponse.brandId());
        assertEquals(dtoResponse.priceList(), restResponse.priceList());
        assertEquals(dtoResponse.startDate(), restResponse.startDate());
        assertEquals(dtoResponse.endDate(), restResponse.endDate());
        assertEquals(dtoResponse.price(), restResponse.price());
    }

    @Test
    void testToRestResponse_NullDtoResponse() {
        PricingDtoResponse dtoResponse = null;

        GetPricingRestResponse restResponse = mapper.toRestResponse(dtoResponse);

        assertNull(restResponse);
    }
}