package ru.simakov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.simakov.clients.fraud.FraudCheckResponse;
import ru.simakov.model.entity.FraudCheckHistory;
import ru.simakov.repository.FraudCheckHistoryRepository;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckResponse isFraudulentCustomer(Long customerId) {

        var fraudCheckHistory = fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .build());


        return FraudCheckResponse.builder()
                .id(fraudCheckHistory.getId())
                .isFraudster(fraudCheckHistory.getIsFraudster())
                .build();
    }
}
