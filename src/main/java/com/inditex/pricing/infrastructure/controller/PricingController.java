package com.inditex.pricing.infrastructure.controller;

import com.inditex.pricing.application.ports.dto.PricingDtoResponse;
import com.inditex.pricing.application.ports.in.GetPricingInputPort;
import com.inditex.pricing.infrastructure.rest.dto.GetPricingRestResponse;
import com.inditex.pricing.infrastructure.rest.mapper.PricingRestMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PricingController {

    private final GetPricingInputPort getPricingInputPort;
    private final PricingRestMapper mapper;

    public PricingController(GetPricingInputPort getPricingInputPort, PricingRestMapper mapper) {
        this.getPricingInputPort = getPricingInputPort;
        this.mapper = mapper;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello from Pricing Service!";
    }

    @GetMapping("/getPricing")
    public GetPricingRestResponse getPricing(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        PricingDtoResponse response = getPricingInputPort.getPricing(date, productId, brandId);
        return mapper.toRestResponse(response);
    }

}