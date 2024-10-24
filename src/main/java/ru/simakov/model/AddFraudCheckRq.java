package ru.simakov.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddFraudCheckRq {
    private Long id;
    private Boolean isFraudster;
}
