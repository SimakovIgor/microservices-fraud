package ru.simakov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.simakov.clients.fraud.FraudCheckResponse;
import ru.simakov.service.FraudCheckService;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
@Slf4j
public class FraudCheckController {
    private final FraudCheckService fraudCheckService;

    @GetMapping
    public FraudCheckResponse isFraudster(
            final @RequestHeader Long customerId) {
        log.info("New check isFraudster for customerId {}", customerId);
        return fraudCheckService.isFraudulentCustomer(customerId);
    }
}
