package com.inditex.pricing.infrastructure.persistence.mapper;

import com.inditex.pricing.domain.model.Pricing;
import com.inditex.pricing.infrastructure.persistence.entity.PriceEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriceEntityMapperTest {

    private final PriceEntityMapper mapper = new PriceEntityMapper();

    @Test
    void testToDomain() {
        PriceEntity entity = PriceEntity.builder()
                .id(1L)
                .brandId(200L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(1)
                .productId(100L)
                .priority(1)
                .price(29.99)
                .currency("EUR")
                .build();

        Pricing pricing = mapper.toDomain(entity);

        assertEquals(entity.getBrandId(), pricing.getBrandId());
        assertEquals(entity.getProductId(), pricing.getProductId());
        assertEquals(entity.getPriceList(), pricing.getPriceList());
        assertEquals(entity.getStartDate(), pricing.getStartDate());
        assertEquals(entity.getEndDate(), pricing.getEndDate());
        assertEquals(entity.getPrice(), pricing.getPrice());
        assertEquals(entity.getCurrency(), pricing.getCurrency());
        assertEquals(entity.getPriority(), pricing.getPriority());
    }

    @Test
    void testToDomain_NullEntity() {
        Pricing pricing = mapper.toDomain(null);
        assertNull(pricing);
    }
}