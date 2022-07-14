package com.company.Group2GameStore.service;

import com.company.Group2GameStore.controller.TshirtController;
import com.company.Group2GameStore.exceptions.NotFoundException;
import com.company.Group2GameStore.model.Console;
import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.model.Invoice;
import com.company.Group2GameStore.model.Tshirt;
import com.company.Group2GameStore.repository.*;
import com.company.Group2GameStore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
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

    public List<Game> getAllGames(String studio, String esrbRating, String title){
        if (studio != null) {
            return gameRepository.findGamesByStudio(studio);
        }
        if (esrbRating != null) {
            return gameRepository.findGamesByEsrbRating(esrbRating);
        }
        if (title != null) {
            return gameRepository.findGamesByTitle(title);
        }
        return gameRepository.findAll();
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


//    Business Rules
//
//    Sales tax applies only to the cost of the items.
//
//    Sales tax does not apply to any processing fees for an invoice.
//
//    The processing fee is applied only once per order, regardless of the number of items in the order, unless the number of items in the order is greater than 10, in which case an additional processing fee of $15.49 is applied to the order.
//
//    The order-processing logic must properly update the quantity available for the item in the order.
//
//    Order quantity must be greater than zero.
//
//    Order quantity must be less than or equal to the number of items available in inventory.
//
//    The order must contain a valid state code.
//
//    The REST API must properly handle and report all violations of business rules.
//
//    public InvoiceViewModel createInvoice(Invoice invoice){
//        InvoiceViewModel newInvoice = new InvoiceViewModel();
//        newInvoice.setInvoiceId(invoice.getInvoiceId());
//        newInvoice.setCity(invoice.getCity());
//        //etc etc
//       //
//
//   }

}
