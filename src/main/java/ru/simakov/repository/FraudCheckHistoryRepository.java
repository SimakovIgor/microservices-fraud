package ru.simakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.simakov.model.entity.FraudCheckHistory;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Long> {
}
