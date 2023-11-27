package ru.simakov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.simakov.clients.fraud.FraudCheckResponse;
import ru.simakov.service.FraudCheckService;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckController {
    private final FraudCheckService fraudCheckService;

    @GetMapping
    public FraudCheckResponse checkForFraud(final @RequestHeader Long customerId) {
        return fraudCheckService.checkFraud(customerId);
    }
}
