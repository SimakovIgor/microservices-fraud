package ru.simakov.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.simakov.clients.fraud.FraudCheckResponse;
import ru.simakov.model.FraudCheckHistory;
import ru.simakov.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FraudCheckControllerTest extends IntegrationTestBase {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void registerCustomer() {
        assertThat(getFraudCheckResponse("999"))
            .isNotNull();
        assertThat(fraudCheckHistoryRepository.findAll())
            .first()
            .satisfies(fraudCheckHistory -> {
                assertThat(fraudCheckHistory.getId())
                    .isNotNull();
                assertThat(fraudCheckHistory)
                    .extracting(FraudCheckHistory::getCustomerId, FraudCheckHistory::getIsFraudster)
                    .containsExactly(999L, false);
            });
    }

    private FraudCheckResponse getFraudCheckResponse(final String customerId) {
        return webTestClient.get()
            .uri("http://localhost:" + localPort + "/api/v1/fraud-check")
            .header("customerId", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(FraudCheckResponse.class)
            .returnResult()
            .getResponseBody();
    }

}
