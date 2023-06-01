package ru.simakov.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import ru.simakov.BaseEntity;
import ru.simakov.BaseEntityC;
import ru.simakov.BaseEntityCU;

@SuperBuilder
@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
public class FraudCheckHistory extends BaseEntityCU {
    private Long customerId;
    private Boolean isFraudster;
}
