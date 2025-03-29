package com.inditex.pricing.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record GetPricingRestResponse(
        @JsonProperty("product_id") Long productId,
        @JsonProperty("brand_id") Long brandId,
        @JsonProperty("price_list") Integer priceList,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
        Double price
) {}