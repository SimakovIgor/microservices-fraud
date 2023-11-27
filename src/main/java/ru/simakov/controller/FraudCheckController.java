package ru.simakov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.simakov.commons.model.internal.fraud.FraudCheckResponse;
import ru.simakov.service.FraudCheckService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FraudCheckController {
    private final FraudCheckService fraudCheckService;

    @GetMapping("/api/v1/fraud-check")
    public FraudCheckResponse checkForFraud(final @RequestParam Long customerId) {
        return fraudCheckService.checkFraud(customerId);
    }
}
