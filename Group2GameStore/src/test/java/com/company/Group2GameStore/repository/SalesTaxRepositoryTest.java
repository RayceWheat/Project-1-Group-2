package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.SalesTaxRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesTaxRepositoryTest {

    @Autowired
    SalesTaxRepository taxRepo;

    @Before
    public void setUp() throws Exception {
        taxRepo.deleteAll();
    }

    @Test
    public void getAllTaxRates() {
        taxRepo.save(new SalesTaxRate("MA", new BigDecimal("0.05")));
        taxRepo.save(new SalesTaxRate("NV", new BigDecimal("0.03")));
        taxRepo.save(new SalesTaxRate("SD", new BigDecimal("0.01")));
        taxRepo.save(new SalesTaxRate("FL", new BigDecimal("0.09")));

        List<SalesTaxRate> stateList = taxRepo.findAll();
        assertEquals(4, stateList.size());
    }

    @Test
    public void updateTaxRates() {
        BigDecimal taxHike = new BigDecimal("0.30");

        SalesTaxRate taxRaise = taxRepo.save(new SalesTaxRate("TX", new BigDecimal("0.03")));
        taxRaise.setRate(taxHike);
        SalesTaxRate highTax = taxRepo.save(taxRaise);
        assertEquals(highTax.getRate(), taxHike);
    }
}