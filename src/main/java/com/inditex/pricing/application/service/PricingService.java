package com.inditex.pricing.application.service;

import com.inditex.pricing.application.mapper.PricingServiceMapper;
import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.application.ports.in.GetPricingInputPort;
import com.inditex.pricing.application.ports.out.PricingPersistenceOutputPort;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PricingService implements GetPricingInputPort {

    private final PricingPersistenceOutputPort pricingPersistenceOutputPort;
    private final PricingServiceMapper pricingServiceMapper;

    public PricingService(PricingPersistenceOutputPort pricingPersistenceOutputPort, PricingServiceMapper pricingServiceMapper) {
        this.pricingPersistenceOutputPort = pricingPersistenceOutputPort;
        this.pricingServiceMapper = pricingServiceMapper;
    }

    /**
     * Retrieves the pricing information for a given product and brand at a specific date.
     *
     * @param applicationDate the date for which to retrieve the pricing information
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @return the pricing information
     * @throws PriceNotFoundException if no pricing information is found for the given parameters
     */
    @Override
    public PricingDtoResponse getPricing(LocalDateTime applicationDate, Long productId, Long brandId) throws PriceNotFoundException {
        var domainPricing = pricingPersistenceOutputPort.findByBrandIdAndProductIdAndDate(applicationDate, productId, brandId);
        if (domainPricing.isEmpty()) {
            throw new PriceNotFoundException("No pricing information found for the given parameters.");
        }
        return pricingServiceMapper.fromDomain(domainPricing.get());
    }
}
