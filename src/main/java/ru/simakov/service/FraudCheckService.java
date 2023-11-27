package ru.simakov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.simakov.commons.model.internal.fraud.FraudCheckResponse;
import ru.simakov.model.FraudCheckHistory;
import ru.simakov.repository.FraudCheckHistoryRepository;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    private static FraudCheckResponse mapFraudCheckResponse(final FraudCheckHistory fraudCheckHistory) {
        return FraudCheckResponse.builder()
            .id(fraudCheckHistory.getId())
            .isFraud(fraudCheckHistory.getIsFraudster())
            .build();
    }

    private static FraudCheckHistory buildFraudCheckHistory(final Long customerId) {
        return FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .build();
    }

    public FraudCheckResponse checkFraud(final Long customerId) {
        final FraudCheckHistory fraudCheckHistory = buildFraudCheckHistory(customerId);
        final FraudCheckHistory saved = fraudCheckHistoryRepository.save(fraudCheckHistory);
        return mapFraudCheckResponse(saved);
    }
}
