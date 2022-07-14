package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.ProcessingFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFees, Integer> {

    BigDecimal findProcessingFeeByProductType(String productType);

}
