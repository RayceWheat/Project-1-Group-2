package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.model.SalesTaxRate;
import com.company.Group2GameStore.repository.SalesTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class SalesTaxController {

    @Autowired
    SalesTaxRepository salesTaxRepository;

    // get all state taxes

    @GetMapping("/tax")
    public List<SalesTaxRate> getSalesTax() {

        return salesTaxRepository.findAll();
    }

}