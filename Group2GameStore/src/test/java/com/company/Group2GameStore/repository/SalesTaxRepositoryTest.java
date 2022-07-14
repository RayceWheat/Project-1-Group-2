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
    public void addGetDeleteSalesTax() {
        SalesTaxRate testTax = taxRepo.save(new SalesTaxRate(0, "CT", new BigDecimal("0.03")));

        assertEquals(true, taxRepo.existsById(testTax.getTaxId()));
        taxRepo.deleteById(testTax.getTaxId());
        assertEquals(false, taxRepo.existsById(testTax.getTaxId()));
    }

    @Test
    public void getAllRsvps() {
        taxRepo.save(new SalesTaxRate(0, "MA", new BigDecimal("0.05")));
        taxRepo.save(new SalesTaxRate(0, "NV", new BigDecimal("0.03")));
        taxRepo.save(new SalesTaxRate(0, "SD", new BigDecimal("0.01")));
        taxRepo.save(new SalesTaxRate(0, "FL", new BigDecimal("0.09")));

        List<SalesTaxRate> stateList = taxRepo.findAll();
        assertEquals(4, stateList.size());
    }

    @Test
    public void updateRsvp() {
        BigDecimal taxHike = new BigDecimal("0.30");

        SalesTaxRate taxRaise = taxRepo.save(new SalesTaxRate(0, "TX", new BigDecimal("0.03")));
        taxRaise.setRate(taxHike);
        SalesTaxRate highTax = taxRepo.save(taxRaise);
        assertEquals(highTax.getRate(), taxHike);
    }
}