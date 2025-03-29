package com.inditex.pricing.infrastructure.persistence.adapter;

import com.inditex.pricing.application.ports.out.PricingPersistenceOutputPort;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Pricing;
import com.inditex.pricing.infrastructure.persistence.PricingJpaRepository;
import com.inditex.pricing.infrastructure.persistence.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Component
@RequiredArgsConstructor
public class PricingH2Adapter implements PricingPersistenceOutputPort {

    private final PricingJpaRepository repository;
    private final PriceEntityMapper mapper;

    @Override
    public Optional<Pricing> findByBrandIdAndProductIdAndDate(LocalDateTime applicationDate, Long productId, Long brandId) throws PriceNotFoundException {
        var entity = repository.findByBrandIdAndProductIdAndDate(brandId, productId, applicationDate);
        return Optional.ofNullable(mapper.toDomain(entity));
    }
}
