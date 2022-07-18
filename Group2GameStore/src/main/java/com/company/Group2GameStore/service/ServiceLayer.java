package com.company.Group2GameStore.service;

import com.company.Group2GameStore.controller.TshirtController;
import com.company.Group2GameStore.exceptions.NotFoundException;
import com.company.Group2GameStore.model.*;
import com.company.Group2GameStore.repository.*;
import com.company.Group2GameStore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private ConsoleRepository consoleRepository;

    private GameRepository gameRepository;

    private InvoiceRepository invoiceRepository;

    private ProcessingFeeRepository processingFeeRepository;

    private SalesTaxRepository salesTaxRepository;

    private TshirtRepository tshirtRepository;

    @Autowired

    public ServiceLayer(ConsoleRepository consoleRepository, GameRepository gameRepository, InvoiceRepository invoiceRepository, ProcessingFeeRepository processingFeeRepository, SalesTaxRepository salesTaxRepository, TshirtRepository tshirtRepository) {
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.invoiceRepository = invoiceRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.salesTaxRepository = salesTaxRepository;
        this.tshirtRepository = tshirtRepository;
    }


    @Transactional

    public List<Console> getAllConsoles(){
        return consoleRepository.findAll();
    }

    public Console getConsoleById(int id){
        Optional<Console> returnVal = consoleRepository.findById(id);

        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            throw new NotFoundException("Console not found in database");
        }
    }

    public List<Console> getConsoleByManufacturer(String manufacturer){
        return consoleRepository.findConsoleByManufacturer(manufacturer);
    }

    public Console createNewConsole(Console console){
        return consoleRepository.save(console);
    }

    public void updateConsole(Console console){
        consoleRepository.save(console);
    }

    public Console updateConsoleById(int id, Console console){
        if(console.getId() == 0) {
            console.setId(id);
        }

        if(console.getId() != id) {
            throw new IllegalArgumentException("Id in parameter must match the ID in the request body");
        }

        if (console.getId() == id){
            return consoleRepository.save(console);
        }
        return null;
    }

    public void deleteConsoleById(int id){
        consoleRepository.deleteById(id);
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public List<Game> getGamesByTitle(String title){
        return gameRepository.findGamesByTitle(title);
    }

    public List<Game> getGamesByEsrbRating(String esrbRating){
        return gameRepository.findGamesByEsrbRating(esrbRating);
    }

    public List<Game> getGamesByStudio(String studio){
        return gameRepository.findGamesByStudio(studio);
    }

    public Game addGame(Game game){
        return gameRepository.save(game);
    }

    public Game findGameById(int id){
        Optional<Game> game = gameRepository.findById(id);
        // we use the optional type in case we get an empty response

        if (game.isPresent() == false) {
            throw new IllegalArgumentException("invalid id");
        }
        return game.get();
    }

    public void updateGame(Game game){
        gameRepository.save(game);
    }

    public Game updateGameById(int id, Game game){
        if(game.getGameId() == 0) {
            game.setGameId(id);
        }

        if(game.getGameId() != id) {
            throw new IllegalArgumentException("Id in parameter must match the ID in the request body");
        }

        if (game.getGameId() == id){
            return gameRepository.save(game);
        }
        return null;
    }

    public void deleteGameById(int id){
        gameRepository.deleteById(id);
    }

    public Tshirt createTShirt(Tshirt tshirt){
        return tshirtRepository.save(tshirt);
    }

    public List<Tshirt> getAllTShirts(String color, String size){
        if (color != null) {
            return tshirtRepository.findByColor(color);
        }
        if (size != null) {
            return tshirtRepository.findBySize(size);
        }
        if (color != null && size != null) {
            return tshirtRepository.findByColorAndSize(color, size);
        }
        return tshirtRepository.findAll();
    }

    public Tshirt getTshirtById(int id){
        Optional<Tshirt> tshirt = tshirtRepository.findById(id);
        if(tshirt.isPresent() == false) {
            throw new IllegalArgumentException("Invalid Id");
        }

        return tshirt.get();
    }

    public void updateTshirtById(int id, Tshirt tshirt){
        if (tshirt.gettShirtId() == null) {
            tshirt.settShirtId(id);
        } else if (tshirt.gettShirtId() != id) {
            throw new IllegalArgumentException("Id does not match");
        }

        tshirtRepository.save(tshirt);
    }

    public void deleteTshirtById(int id){
        tshirtRepository.deleteById(id);
   }


   public InvoiceViewModel getInvoiceById(int id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice == null){
            throw new NotFoundException("Invoice with that ID does not exists");
        } else {
            return buildInvoiceViewModel(invoice.get());
        }

   }

   public void updateInvoiceById(InvoiceViewModel ivm){

            Invoice invoice1 = new Invoice();
            invoice1.setInvoiceId(ivm.getInvoiceId());
            invoice1.setName(ivm.getName());
            invoice1.setStreet(ivm.getStreet());
            invoice1.setCity(ivm.getCity());
            invoice1.setState(ivm.getState());
            invoice1.setZipCode(ivm.getZipCode());
            invoice1.setItemType(ivm.getItemType());
            invoice1.setQuantity(ivm.getQuantity());
            invoice1.setUnitPrice(ivm.getUnitPrice());
            invoice1.setItemId(ivm.getItemId());
            invoice1.setSubtotal(ivm.getSubtotal());
            invoice1.setTax(ivm.getTax());
            invoice1.setProcessingFee(ivm.getProcessingFee());
            invoice1.setTotal(ivm.getTotal());

            invoiceRepository.save(invoice1);

   }

    public void deleteInvoiceById(int id){
        invoiceRepository.deleteById(id);
    }

   public InvoiceViewModel createANewInvoice(InvoiceViewModel ivm){
       if(ivm.getQuantity() <= 0){
           throw new IllegalArgumentException("Quantity must be greater than zero");
       }

       switch(ivm.getItemType()){
           case "Games":
               Optional<Game> foundGame = gameRepository.findById(ivm.getItemId());
               if(ivm.getQuantity() > foundGame.get().getQuantity()){
                   throw new IllegalArgumentException("You can't buy more games than we have in stock");
               } else {
                   foundGame.get().setQuantity(foundGame.get().getQuantity() - ivm.getQuantity());
                   gameRepository.save(foundGame.get());
                   ivm.setUnitPrice(foundGame.get().getPrice());
               }
               break;
           case "T-shirts":
               Optional<Tshirt> foundTshirt = tshirtRepository.findById(ivm.getItemId());
               if(ivm.getQuantity() > foundTshirt.get().getQuantity()){
                   throw new IllegalArgumentException("Out of stock");
               } else {
                   foundTshirt.get().setQuantity(foundTshirt.get().getQuantity() - ivm.getQuantity());
                   tshirtRepository.save(foundTshirt.get());
                   ivm.setUnitPrice((foundTshirt.get().getPrice()));
               }
               break;
           case "Consoles":
               Optional<Console> foundConsole = consoleRepository.findById(ivm.getItemId());
               if(ivm.getQuantity() > foundConsole.get().getQuantity()){
                   throw new IllegalArgumentException("Out of stock");
               } else {
                   foundConsole.get().setQuantity(foundConsole.get().getQuantity() - ivm.getQuantity());
                   consoleRepository.save(foundConsole.get());
                   ivm.setUnitPrice(foundConsole.get().getPrice());
               }
               break;
           default:
               throw new IllegalArgumentException("We don't sell this");
       }

       BigDecimal quantityAsBigDecmial = new BigDecimal(ivm.getQuantity());

       ivm.setSubtotal(ivm.getUnitPrice().multiply(quantityAsBigDecmial));


       Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findById(ivm.getState());
       System.out.println(salesTaxRate);


       ivm.setTax(salesTaxRate.get().getRate());

       System.out.println(ivm.getSubtotal());
       System.out.println(ivm.getTax());

       BigDecimal originalSubTotal = ivm.getSubtotal();
       BigDecimal taxAmount = ivm.getSubtotal().multiply(ivm.getTax());

       System.out.println(ivm.getSubtotal());

       Optional<ProcessingFees> processingFeesOptional = processingFeeRepository.findById(ivm.getItemType());

       ivm.setProcessingFee(processingFeesOptional.get().getFee());

       if(ivm.getQuantity() > 10){
           ivm.setProcessingFee(ivm.getProcessingFee().add(new BigDecimal("15.49")));
       }

       ivm.setTotal(ivm.getSubtotal().add(ivm.getProcessingFee()));


       Invoice invoice = new Invoice();


       System.out.println(invoice.getInvoiceId());

       invoice.setName(ivm.getName());
       invoice.setStreet(ivm.getStreet());
       invoice.setCity(ivm.getCity());
       invoice.setState(ivm.getState());
       invoice.setZipCode(ivm.getZipCode());
       invoice.setItemType(ivm.getItemType());
       invoice.setQuantity(ivm.getQuantity());
       invoice.setItemId(ivm.getItemId());
       invoice.setUnitPrice(ivm.getUnitPrice());
       invoice.setSubtotal(ivm.getSubtotal());
       invoice.setTax(ivm.getTax());
       invoice.setProcessingFee(ivm.getProcessingFee());
       invoice.setTotal(ivm.getTotal());


       invoice = invoiceRepository.save(invoice);

       ivm.setInvoiceId(invoice.getInvoiceId());

       return ivm;

   }

    public List<InvoiceViewModel> getAllInvoices(){

        List<Invoice> invoiceList = invoiceRepository.findAll();

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for(Invoice invoice : invoiceList){
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }

        return ivmList;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){

        InvoiceViewModel newInvoice = new InvoiceViewModel();
        newInvoice.setInvoiceId(invoice.getInvoiceId());
        newInvoice.setName(invoice.getName());
        newInvoice.setStreet(invoice.getStreet());
        newInvoice.setCity(invoice.getCity());
        newInvoice.setState(invoice.getState());
        newInvoice.setZipCode(invoice.getZipCode());
        newInvoice.setItemType(invoice.getItemType());
        newInvoice.setItemId(invoice.getItemId());
        newInvoice.setUnitPrice(invoice.getUnitPrice());
        newInvoice.setQuantity(invoice.getQuantity());
        newInvoice.setSubtotal((invoice.getSubtotal()));

        newInvoice.setTax(invoice.getTax());
        newInvoice.setProcessingFee(invoice.getProcessingFee());
        newInvoice.setTotal(invoice.getTotal());

        return newInvoice;
   }

}