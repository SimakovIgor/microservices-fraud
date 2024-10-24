package ru.simakov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.simakov.commons.model.internal.fraud.FraudCheckResponse;
import ru.simakov.service.FraudCheckService;

import java.time.LocalDateTime;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class FraudCheckController {
    private final FraudCheckService fraudCheckService;

    @GetMapping("/api/v1/fraud-check")
    public FraudCheckResponse checkForFraud(final @RequestParam("customerId") Long customerId) {
        return fraudCheckService.checkFraud(customerId);
    }

    @GetMapping("/health")
    public String checkForFraud() {
        final LocalDateTime now = LocalDateTime.now();
        log.info("Healthy {}", now);
        return "Healthy: " + now;
    }
}
