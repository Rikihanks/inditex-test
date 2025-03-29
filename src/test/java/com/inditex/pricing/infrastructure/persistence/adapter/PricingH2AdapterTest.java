package com.inditex.pricing.infrastructure.persistence.adapter;

import com.inditex.pricing.application.mapper.PricingServiceMapper;
import com.inditex.pricing.domain.model.Pricing;
import com.inditex.pricing.infrastructure.persistence.PricingJpaRepository;
import com.inditex.pricing.infrastructure.persistence.entity.PriceEntity;
import com.inditex.pricing.infrastructure.persistence.mapper.PriceEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class PricingH2AdapterTest {

    private PricingH2Adapter adapter;

    @Mock
    private PricingJpaRepository jpaRepository;

    @Mock
    private PriceEntityMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        adapter = new PricingH2Adapter(jpaRepository, mapper);
    }

    @Test
    void shouldReturnMappedPrices() {
        when(jpaRepository.findByBrandIdAndProductIdAndDate(any(), any(), any()))
                .thenReturn(new PriceEntity());
        when(mapper.toDomain(any())).thenReturn(Pricing.builder()
                .price(21.4)
                .brandId(1L)
                .productId(1L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priority(1)
                .build());

        var res = adapter.findByBrandIdAndProductIdAndDate(LocalDateTime.now(),1L, 1L);

        assertThat(res).isNotNull();
    }
}