package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    ConsoleRepository consoleRepository;
    @Autowired
    TshirtRepository tshirtRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    SalesTaxRepository salesTaxRepository;
    @Autowired
    ProcessingFeeRepository processingFeeRepository;

    @Before
    public void setUp() throws Exception {
        gameRepository.deleteAll();
        consoleRepository.deleteAll();
        tshirtRepository.deleteAll();
        invoiceRepository.deleteAll();
    }

    @Test
    public void addGetDeleteInvoice() {
        //save a new game to use in .setItemId()
        Game newGame = new Game();
        newGame.setTitle("Game of Thrones");
        newGame.setEsrbRating("Mature");
        newGame.setDescription("Awesome Game with numerous alternate endings.");
        newGame.setPrice(new BigDecimal("14.99"));
        newGame.setStudio("Warner Bros Entertainment");
        newGame.setQuantity(25);

        newGame = gameRepository.save(newGame);

        Invoice invoice = new Invoice();
        invoice.setName("PurchaseReceipt1");
        invoice.setStreet("Park Ave");
        invoice.setCity("New York");
        invoice.setState("NY");
        invoice.setZipCode("10000");
        invoice.setItemType("Games");
        invoice.setUnitPrice(newGame.getPrice());
        invoice.setItemId(newGame.getGameId());

        //subtotal
        BigDecimal quantityAsBigDecimal = new BigDecimal(newGame.getQuantity());
        invoice.setSubtotal(invoice.getUnitPrice().multiply(quantityAsBigDecimal));

        //taxed
        Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findById(invoice.getState());
        invoice.setTax(salesTaxRate.get().getRate());
        BigDecimal taxAmount = invoice.getSubtotal().multiply(invoice.getTax());
        BigDecimal taxTotal = taxAmount.add(invoice.getSubtotal());

        invoice.setTax(taxTotal);//taxRepo

        //processing fee added
        Optional<ProcessingFees> processingFeesOptional = processingFeeRepository.findById(invoice.getItemType());
        invoice.setProcessingFee(processingFeesOptional.get().getFee());

        //quantity
        invoice.setQuantity(1);

        invoice.setTotal(new BigDecimal(414.215));//calculate

        invoice = invoiceRepository.save(invoice);
//        Invoice invoice2
        assertEquals(true, invoiceRepository.existsById(invoice.getInvoiceId()));

        //delete
        invoiceRepository.deleteById(invoice.getInvoiceId());

        //check no longer exist
        assertEquals(false, invoiceRepository.existsById(invoice.getInvoiceId()));

    }

    //get all
    @Test
    public void shouldReturnAllInvoices() {
        Game newGame = new Game();
        newGame.setTitle("Game of Thrones");
        newGame.setEsrbRating("Mature");
        newGame.setDescription("Awesome Game with numerous alternate endings.");
        newGame.setPrice(new BigDecimal("14.99"));
        newGame.setStudio("Warner Bros Entertainment");
        newGame.setQuantity(25);

        newGame = gameRepository.save(newGame);
        Invoice invoice = new Invoice();
        invoice.setName("PurchaseReceipt1");
        invoice.setStreet("Park Ave");
        invoice.setCity("New York");
        invoice.setState("NY");
        invoice.setZipCode("10000");
        invoice.setItemType("Games");
        invoice.setUnitPrice(newGame.getPrice());
        invoice.setItemId(newGame.getGameId());

        //subtotal
        BigDecimal quantityAsBigDecimal = new BigDecimal(newGame.getQuantity());
        invoice.setSubtotal(invoice.getUnitPrice().multiply(quantityAsBigDecimal));

        //taxed
        Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findById(invoice.getState());
        invoice.setTax(salesTaxRate.get().getRate());
        BigDecimal taxAmount = invoice.getSubtotal().multiply(invoice.getTax());
        BigDecimal taxTotal = taxAmount.add(invoice.getSubtotal());

        invoice.setTax(taxTotal);//taxRepo

        //processing fee added
        Optional<ProcessingFees> processingFeesOptional = processingFeeRepository.findById(invoice.getItemType());
        invoice.setProcessingFee(processingFeesOptional.get().getFee());

        //quantity
        invoice.setQuantity(1);

        invoice.setTotal(new BigDecimal(414.215));//calculate

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(2);
        invoice2.setName("PurchaseReceipt1");
        invoice2.setStreet("Park Ave");
        invoice2.setCity("New York");
        invoice2.setState("NY");
        invoice2.setZipCode("10000");
        invoice2.setItemType("Games");
        invoice2.setUnitPrice(newGame.getPrice());
        invoice2.setItemId(newGame.getGameId());

        //subtotal
        BigDecimal quantityAsBigDecimal2 = new BigDecimal(newGame.getQuantity());
        invoice2.setSubtotal(invoice2.getUnitPrice().multiply(quantityAsBigDecimal2));

        //taxed
        Optional<SalesTaxRate> salesTaxRate2 = salesTaxRepository.findById(invoice2.getState());
        invoice2.setTax(salesTaxRate2.get().getRate());
        BigDecimal taxAmount2 = invoice2.getSubtotal().multiply(invoice2.getTax());
        BigDecimal taxTotal2 = taxAmount2.add(invoice2.getSubtotal());

        invoice2.setTax(taxTotal2);//taxRepo

        //processing fee added
        Optional<ProcessingFees> processingFeesOptional2 = processingFeeRepository.findById(invoice2.getItemType());
        invoice2.setProcessingFee(processingFeesOptional2.get().getFee());

        //quantity
        invoice2.setQuantity(1);

        invoice2.setTotal(new BigDecimal(414.215));//calculate

        invoiceRepository.save(invoice);
        invoiceRepository.save(invoice2);

        //second object doesn't need to be saved to variable

        List<Invoice> list = invoiceRepository.findAll();

        assertEquals(2, list.size());
    }



  //update test
    @Test
    public void shouldUpdateInvoice() {

        Game newGame = new Game();
        newGame.setTitle("Game of Thrones");
        newGame.setEsrbRating("Mature");
        newGame.setDescription("Awesome Game with numerous alternate endings.");
        newGame.setPrice(new BigDecimal("14.99"));
        newGame.setStudio("Warner Bros Entertainment");
        newGame.setQuantity(25);

        newGame = gameRepository.save(newGame);

        Invoice invoice = new Invoice();
        invoice.setName("PurchaseReceipt1");
        invoice.setStreet("Park Ave");
        invoice.setCity("New York");
        invoice.setState("NY");
        invoice.setZipCode("10000");
        invoice.setItemType("Games");
        invoice.setUnitPrice(newGame.getPrice());
        invoice.setItemId(newGame.getGameId());

        //subtotal
        BigDecimal quantityAsBigDecimal = new BigDecimal(newGame.getQuantity());
        invoice.setSubtotal(invoice.getUnitPrice().multiply(quantityAsBigDecimal));

        //taxed
        Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findById(invoice.getState());
        invoice.setTax(salesTaxRate.get().getRate());
        BigDecimal taxAmount = invoice.getSubtotal().multiply(invoice.getTax());
        BigDecimal taxTotal = taxAmount.add(invoice.getSubtotal());

        invoice.setTax(taxTotal);//taxRepo

        //processing fee added
        Optional<ProcessingFees> processingFeesOptional = processingFeeRepository.findById(invoice.getItemType());
        invoice.setProcessingFee(processingFeesOptional.get().getFee());

        //quantity
        invoice.setQuantity(1);

        invoice.setTotal(new BigDecimal(414.215));//calculate


        invoice.setName("Receipt");

        Invoice invoiceUpdate = invoiceRepository.save(invoice);
//
//        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.gettShirtId());
//        assertEquals(tshirt1.get(), tshirt);
        assertEquals(invoiceUpdate.getName(), "Receipt");

    }

    //integrity test - happy pass, sad handle 500/400 exception

    //second try
//    @Test(expected  = DataIntegrityViolationException.class)
//    @ExpectedException(org.springframework.dao.DataIntegrityViolationException.class)
//    public void addWithRefIntegrityException() {
//
//        Game newGame = new Game();
//        newGame.setGameId(1);
//        newGame.setTitle("Game of Thrones");
//        newGame.setEsrbRating("Mature");
//        newGame.setDescription("Awesome Game with numerous alternate endings.");
//        newGame.setPrice(new BigDecimal("14.99"));
//        newGame.setStudio("Warner Bros Entertainment");
//        newGame.setQuantity(25);
//
//        newGame = gameRepository.save(newGame);
//
//        Invoice invoice = new Invoice();
//        invoice.setName("PurchaseReceipt1");
//        invoice.setStreet("Park Ave");
//        invoice.setCity("New York");
//        invoice.setState("NY");
//        invoice.setZipCode("10000");
//        invoice.setItemType("Games");
//        invoice.setUnitPrice(new BigDecimal(15.0));
//        invoice.setItemId(-200);
//
//        //subtotal
//        BigDecimal quantityAsBigDecimal = new BigDecimal(newGame.getQuantity());
//        invoice.setSubtotal(invoice.getUnitPrice().multiply(quantityAsBigDecimal));
//
//        //taxed
//        Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findById(invoice.getState());
//        invoice.setTax(salesTaxRate.get().getRate());
//        BigDecimal taxAmount = invoice.getSubtotal().multiply(invoice.getTax());
//        BigDecimal taxTotal = taxAmount.add(invoice.getSubtotal());
//
//        invoice.setTax(taxTotal);//taxRepo
//
//        //processing fee added
//        Optional<ProcessingFees> processingFeesOptional = processingFeeRepository.findById(invoice.getItemType());
//        invoice.setProcessingFee(processingFeesOptional.get().getFee());
//
//        //quantity
//        invoice.setQuantity(1);
//
//        invoice.setTotal(new BigDecimal(414.215));//calculate
//
//        invoice = invoiceRepository.save(invoice);
////
////        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.gettShirtId());
////        assertEquals(tshirt1.get(), tshirt);
////        assertEquals(invoiceUpdate.getName(), "Receipt");
//
//    }

    //first try
//    @Test(expected  = DataIntegrityViolationException.class)
//    public void addWithRefIntegrityException() {
//        Invoice invoice = new Invoice();
//        invoice.setName("PurchaseReceipt1");
//        invoice.setStreet("Park Ave");
//        invoice.setCity("New York");
//        invoice.setState("NY");
//        invoice.setZipCode("10000");
//        invoice.setItemType("None");
//        invoice.setUnitPrice(new BigDecimal(15.0));
//        invoice.setItemId(1);
//
//        //quantity
//        invoice.setQuantity(1);
//        //subtotal
////        BigDecimal quantityAsBigDecimal = new BigDecimal(newGame.getQuantity());
//        invoice.setSubtotal(new BigDecimal(15.0));
//
//        //taxed
////        Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findById(invoice.getState());
////        invoice.setTax();
////        BigDecimal taxAmount = invoice.getSubtotal().multiply(invoice.getTax());
////        BigDecimal taxTotal = taxAmount.add(invoice.getSubtotal());
//
//        invoice.setTax(new BigDecimal(15.9));//taxRepo
//
//        //processing fee added
//        Optional<ProcessingFees> processingFeesOptional = processingFeeRepository.findById(invoice.getItemType());
//        invoice.setProcessingFee(new BigDecimal(14.99));
//
//
//        invoice.setTotal(new BigDecimal(30.89));//calculate
//
//        invoice = invoiceRepository.save(invoice);
//
//    }
}
