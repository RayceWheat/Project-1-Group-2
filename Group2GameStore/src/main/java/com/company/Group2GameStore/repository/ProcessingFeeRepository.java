package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.ProcessingFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFees, Integer> {
}
