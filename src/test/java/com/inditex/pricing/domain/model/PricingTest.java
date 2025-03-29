package com.inditex.pricing.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PricingTest {

    @Test
    void testPricingBuilder() {
        Long id = 1L;
        Long brandId = 200L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        Integer priceList = 1;
        Long productId = 100L;
        Integer priority = 1;
        Double price = 29.99;
        String currency = "EUR";

        Pricing pricing = Pricing.builder()
                .id(id)
                .brandId(brandId)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .productId(productId)
                .priority(priority)
                .price(price)
                .currency(currency)
                .build();

        assertEquals(id, pricing.getId());
        assertEquals(brandId, pricing.getBrandId());
        assertEquals(startDate, pricing.getStartDate());
        assertEquals(endDate, pricing.getEndDate());
        assertEquals(priceList, pricing.getPriceList());
        assertEquals(productId, pricing.getProductId());
        assertEquals(priority, pricing.getPriority());
        assertEquals(price, pricing.getPrice());
        assertEquals(currency, pricing.getCurrency());
    }
}