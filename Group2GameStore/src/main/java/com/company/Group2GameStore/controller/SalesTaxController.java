package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.SalesTaxRate;
import com.company.Group2GameStore.model.SalesTaxRate;
import com.company.Group2GameStore.repository.GameRepository;
import com.company.Group2GameStore.repository.SalesTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SalesTaxController {

    @Autowired
    SalesTaxRepository salesTaxRepository;

    // get all state taxes
    @RequestMapping(value = "/tax", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SalesTaxRate> getAllSalesTax() {

        return salesTaxRepository.findAll();

    }

    // add new state tax
    @RequestMapping(value = "/tax", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public SalesTaxRate addGame (@RequestBody SalesTaxRate salesTaxRate) {

        return salesTaxRepository.save(salesTaxRate);
    }

    // find one salesTaxRate
    @RequestMapping(value="/tax/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SalesTaxRate findGamesById(@PathVariable int id) {
        Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findById(id);
        // we use the optional type in case we get an empty response

        if (salesTaxRate.isPresent() == false) {
            throw new IllegalArgumentException("invalid id");
        }
        return salesTaxRate.get();
    }


    // update the salesTaxRate
    @RequestMapping(value = "/tax", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateGame(@RequestBody SalesTaxRate salesTaxRate) {
        salesTaxRepository.save(salesTaxRate);
    }

    // delete salesTaxRate
    @RequestMapping(value = "/tax/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteGame(@PathVariable int id) {
        salesTaxRepository.deleteById(id);
    }


}
