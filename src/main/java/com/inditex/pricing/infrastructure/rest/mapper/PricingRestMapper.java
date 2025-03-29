package com.inditex.pricing.infrastructure.rest.mapper;

import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.infrastructure.rest.dto.GetPricingRestResponse;
import org.springframework.stereotype.Component;


@Component
public class PricingRestMapper {

    public GetPricingRestResponse toRestResponse(PricingDtoResponse domainResponse) {
        if (domainResponse == null) {
            return null;
        }
        return new GetPricingRestResponse(
                domainResponse.productId(),
                domainResponse.brandId(),
                domainResponse.priceList(),
                domainResponse.startDate(),
                domainResponse.endDate(),
                domainResponse.price()
        );
    }

}