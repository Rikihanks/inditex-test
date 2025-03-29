package com.inditex.pricing.infrastructure.persistence.mapper;

import com.inditex.pricing.domain.model.Pricing;
import com.inditex.pricing.infrastructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceEntityMapper {

    public Pricing toDomain(PriceEntity entity) {
        if (entity == null) {
            return null;
        }
        return Pricing.builder()
                .brandId(entity.getBrandId())
                .productId(entity.getProductId())
                .priceList(entity.getPriceList())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .priority(entity.getPriority())
                .build();
    }
}
