package ru.simakov.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.simakov.clients.fraud.FraudCheckResponse;
import ru.simakov.controller.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FraudCheckControllerTest extends IntegrationTestBase {
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void beforeEach() {
        this.restTemplateBuilder = new RestTemplateBuilder()
            .rootUri("http://localhost:" + localPort);
    }

    @SneakyThrows
    @Test
    void registerCustomer() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("customerId", "1");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder);
        ResponseEntity<FraudCheckResponse> responseEntity =
            testRestTemplate.exchange("/api/v1/fraud-check", HttpMethod.GET,
                entity, FraudCheckResponse.class);

        assertThat(responseEntity.getStatusCode())
            .isEqualTo(HttpStatus.OK);
        assertThat(fraudCheckHistoryRepository.findAll())
            .first()
            .satisfies(fraudCheckHistory -> {
                assertThat(fraudCheckHistory.getCustomerId())
                    .isEqualTo(1L);
                assertThat(fraudCheckHistory.getId())
                    .isNotNull();
                assertThat(fraudCheckHistory.getIsFraudster())
                    .isFalse();
            });
    }

}
