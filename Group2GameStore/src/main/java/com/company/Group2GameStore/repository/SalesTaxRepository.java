package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.model.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesTaxRepository extends JpaRepository<SalesTaxRate, Integer> {

    BigDecimal findTaxByState(String state);
    List<SalesTaxRate> findTaxByRate(BigDecimal rate);

}
