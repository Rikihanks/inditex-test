package com.inditex.pricing.application.ports.dto;

import java.time.LocalDateTime;

public record PricingDtoResponse(
        Long productId,
        Long brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price
) {}