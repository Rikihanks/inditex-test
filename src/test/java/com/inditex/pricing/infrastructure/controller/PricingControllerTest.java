package com.inditex.pricing.infrastructure.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class PricingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/prices/test")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello from Pricing Service!")));
    }

    @Test
    void shouldReturn200WhenPriceExists() throws Exception {
        mockMvc.perform(get("/api/prices/getPricing")
                        .param("date", "2020-06-15 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.brand_id").value(1));
    }

    @Test
    void shouldReturn404WhenPriceNotFound() throws Exception {
        mockMvc.perform(get("/api/prices/getPricing")
                        .param("date", "2023-01-01T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound());
    }


    /*
    * The test cases are based on the following scenarios:
    *
    * Please bear in mind that these test are based on the data present in the database, so if the data is changed, the test cases may fail.
    *
    * Adicionalmente, se espera que desarrolles pruebas para el endpoint REST que validen las siguientes solicitudes al servicio utilizando los datos de ejemplo proporcionados:
        1. Prueba 1: Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
        2. Prueba 2: Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
        3. Prueba 3: Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
        4. Prueba 4: Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA).
        5. Prueba 5: Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA).
     */


    @Test
    @DisplayName("Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA)")
    void test1() throws Exception {
        mockMvc.perform(get("/api/prices/getPricing")
                        .param("date", "2020-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.5));
    }

    @Test
    @DisplayName("Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA).")
    void test2() throws Exception {
        mockMvc.perform(get("/api/prices/getPricing")
                        .param("date", "2020-06-14 16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA).")
    void test3() throws Exception {
        mockMvc.perform(get("/api/prices/getPricing")
                        .param("date", "2020-06-14 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.5));
    }

    @Test
    @DisplayName(" Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA).")
    void test4() throws Exception {
        mockMvc.perform(get("/api/prices/getPricing")
                        .param("date", "2020-06-15 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.5));
    }

    @Test
    @DisplayName("Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA).")
    void test5() throws Exception {
        mockMvc.perform(get("/api/prices/getPricing")
                        .param("date", "2020-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }
}