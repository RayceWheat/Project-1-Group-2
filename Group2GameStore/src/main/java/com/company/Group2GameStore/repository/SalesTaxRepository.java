package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.model.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface SalesTaxRepository extends JpaRepository<SalesTaxRate, Integer> {

    List<SalesTaxRate> findTaxByState(String state);
    List<SalesTaxRate> findTaxByRate(BigDecimal rate);

}
