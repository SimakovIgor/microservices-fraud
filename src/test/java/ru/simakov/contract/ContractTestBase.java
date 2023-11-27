package ru.simakov.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.simakov.commons.model.internal.fraud.FraudCheckResponse;
import ru.simakov.controller.FraudCheckController;
import ru.simakov.service.FraudCheckService;
import ru.simakov.starter.testing.initializer.PostgreSQLInitializer;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(initializers = PostgreSQLInitializer.class)
@AutoConfigureMessageVerifier
public abstract class ContractTestBase {
    @Autowired
    private FraudCheckController fraudCheckController;
    @MockBean
    private FraudCheckService fraudCheckService;

    @BeforeEach
    void beforeEach() {
        when(fraudCheckService.checkFraud(1L))
            .thenReturn(FraudCheckResponse.builder()
                .id(1L)
                .isFraud(false)
                .build());

        RestAssuredMockMvc.standaloneSetup(fraudCheckController);

    }
}
