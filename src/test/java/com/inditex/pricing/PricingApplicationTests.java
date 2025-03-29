package com.inditex.pricing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class PricingApplicationTests {

	@Test
	void testSpringApplicationRunCalled() {
		try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
			PricingApplication.main(new String[]{});

			mockedSpringApplication.verify(() -> SpringApplication.run(eq(PricingApplication.class), any(String[].class)));
		}
	}

}
