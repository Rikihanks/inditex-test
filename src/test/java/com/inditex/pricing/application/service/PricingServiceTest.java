package com.inditex.pricing.application.service;

import com.inditex.pricing.application.mapper.PricingServiceMapper;
import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.application.ports.out.PricingPersistenceOutputPort;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Pricing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

    @Mock
    private PricingPersistenceOutputPort pricingPersistenceOutputPort;

    @Mock
    private PricingServiceMapper pricingServiceMapper;

    @InjectMocks
    private PricingService pricingService;

    private Pricing pricing;
    private PricingDtoResponse pricingDtoResponse;

    @BeforeEach
    void setUp() {
        pricing = Pricing.builder()
                .id(1L)
                .productId(100L)
                .brandId(200L)
                .price(29.99)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priority(1)
                .build();
        pricingDtoResponse = new PricingDtoResponse(
                pricing.getProductId(),
                pricing.getBrandId(),
                1,
                pricing.getStartDate(),
                pricing.getEndDate(),
                pricing.getPrice()
        );
    }

    @Test
    void testGetPricing_Success() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        when(pricingPersistenceOutputPort.findByBrandIdAndProductIdAndDate(any(), any(), any()))
                .thenReturn(Optional.of(pricing));
        when(pricingServiceMapper.fromDomain(pricing)).thenReturn(pricingDtoResponse);

        PricingDtoResponse result = pricingService.getPricing(applicationDate, productId, brandId);

        assertEquals(pricingDtoResponse, result);
    }

    @Test
    void testGetPricing_PriceNotFoundException() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        when(pricingPersistenceOutputPort.findByBrandIdAndProductIdAndDate(any(), any(), any()))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> pricingService.getPricing(applicationDate, productId, brandId));
    }
}