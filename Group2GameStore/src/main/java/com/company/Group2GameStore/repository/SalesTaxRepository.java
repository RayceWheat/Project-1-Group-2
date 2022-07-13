package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Games;
import com.company.Group2GameStore.model.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesTaxRepository extends JpaRepository<SalesTaxRate, Integer> {

}
