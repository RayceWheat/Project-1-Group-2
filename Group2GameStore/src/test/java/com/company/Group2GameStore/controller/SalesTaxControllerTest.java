//package com.company.Group2GameStore.controller;
//
//import com.company.Group2GameStore.model.SalesTaxRate;
//import com.company.Group2GameStore.repository.SalesTaxRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.doReturn;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(SalesTaxRate.class)
//public class SalesTaxControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    SalesTaxRepository taxRepo;
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    private SalesTaxRate taxRate;
//    private String taxJson;
//    private List<SalesTaxRate> allTaxes = new ArrayList<>();
//    private String allTaxesJson;
//
//    @Before
//    public void setup() throws Exception {
//        taxRate = new SalesTaxRate();
//        taxRate.setTaxId(1);
//        taxRate.setState("CT");
//        taxRate.setRate(new BigDecimal("0.03"));
//
//        taxJson = mapper.writeValueAsString(taxRate);
//
//        SalesTaxRate taxRate2 = new SalesTaxRate();
//        taxRate2.setTaxId(2);
//        taxRate2.setState("MA");
//        taxRate2.setRate(new BigDecimal("0.05"));
//
//        allTaxes.add(taxRate);
//        allTaxes.add(taxRate2);
//
//        allTaxesJson = mapper.writeValueAsString(allTaxes);
//
//    }
//
//    @Test
//    public void shouldReturnListOfAllTaxRates() throws Exception {
//        doReturn(allTaxes).when(taxRepo).findAll();
//
//        mockMvc.perform(
//                        get("/tax"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(allTaxesJson)
//                );
//    }
//
//    @Test
//    public void shouldReturnTaxRateByState() throws Exception {
//        doReturn(allTaxes).when(taxRepo).findTaxByState("CT");
//
//        mockMvc.perform(
//                        get("/tax?state=CT"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(allTaxesJson)
//                );
//    }
//
//    @Test
//    public void shouldReturnTaxRate() throws Exception {
//        doReturn(allTaxes).when(taxRepo).findTaxByRate(new BigDecimal("0.03"));
//
//        mockMvc.perform(
//                        get("/tax?rate=0.03"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(allTaxesJson)
//                );
//    }
//
//
//}