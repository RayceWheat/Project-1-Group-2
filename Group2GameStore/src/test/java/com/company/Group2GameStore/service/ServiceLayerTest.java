package com.company.Group2GameStore.service;

import com.company.Group2GameStore.model.Console;
import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.model.Invoice;
import com.company.Group2GameStore.model.Tshirt;
import com.company.Group2GameStore.repository.*;
import com.company.Group2GameStore.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    GameRepository gameRepository;
    ConsoleRepository consoleRepository;
    TshirtRepository tshirtRepository;
    SalesTaxRepository salesTaxRepository;
    ProcessingFeeRepository processingFeeRepository;
    InvoiceRepository invoiceRepository;

    @MockBean
    ServiceLayer service;


    @Before
    public void setUp() throws Exception {
        setUpGameRepositoryMock();
        setUpConsoleRepositoryMock();
        setUpTshirtRepositoryMock();
//        setUpSalesTaxRepositoryMock();
//        setUpProcessingFeeRepositoryMock();
        setUpInvoiceRepositoryMock();

        service = new ServiceLayer(consoleRepository, gameRepository, invoiceRepository, processingFeeRepository, salesTaxRepository, tshirtRepository);

    }

    private void setUpGameRepositoryMock() {
        gameRepository = mock(GameRepository.class);
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Game of Thrones X");
        game.setEsrbRating("Mature");
        game.setDescription("New Characters!");
        game.setPrice(new BigDecimal("99.99"));
        game.setStudio("Studio");
        game.setQuantity(100);


        Game secondGame = new Game();
        secondGame.setTitle("Game of Thrones X");
        secondGame.setEsrbRating("Mature");
        secondGame.setDescription("New Characters!");
        secondGame.setPrice(new BigDecimal("99.99"));
        secondGame.setStudio("Studio");
        secondGame.setQuantity(100);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameRepository).save(secondGame);
        doReturn(Optional.of(game)).when(gameRepository).findById(1);
        doReturn(gameList).when(gameRepository).findAll();
        doReturn(gameList).when(gameRepository).findGamesByStudio("Studio");
        doReturn(gameList).when(gameRepository).findGamesByEsrbRating("Mature");
        doReturn(gameList).when(gameRepository).findGamesByTitle("Game of Thrones X");
    }

    private void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);
        Console console1 = new Console();
        console1.setModel("Switch");
        console1.setManufacturer("Nintendo");
        console1.setMemoryAmount("256gb");
        console1.setProcessor("NotAGoodOne");
        console1.setPrice(new BigDecimal("199.99"));
        console1.setQuantity(5);
        console1.setId(2);

        Console console2 = new Console();
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setMemoryAmount("256gb");
        console2.setProcessor("NotAGoodOne");
        console2.setPrice(new BigDecimal("199.99"));
        console2.setQuantity(5);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1);

        doReturn(console1).when(consoleRepository).save(console2);
        doReturn(Optional.of(console1)).when(consoleRepository).findById(1);
        doReturn(consoleList).when(consoleRepository).findAll();
        doReturn(consoleList).when(consoleRepository).findConsoleByManufacturer("Nintendo");
    }

    private void setUpTshirtRepositoryMock() {
        tshirtRepository = mock(TshirtRepository.class);
        Tshirt tshirt = new Tshirt();
        tshirt.settShirtId(1);
        tshirt.setTshirt("League of Legends");
        tshirt.setColor("gold");
        tshirt.setSize("s");
        tshirt.setDescription("game merch");
        tshirt.setPrice(new BigDecimal(99.99));
        tshirt.setQuantity(100);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setTshirt("League of Legends");
        tshirt2.setColor("gold");
        tshirt2.setSize("s");
        tshirt2.setDescription("game merch");
        tshirt2.setPrice(new BigDecimal(99.99));
        tshirt2.setQuantity(100);

        List<Tshirt> tshirtList = new ArrayList<>();
        tshirtList.add(tshirt);

        doReturn(tshirt).when(tshirtRepository).save(tshirt2);
        doReturn(Optional.of(tshirt)).when(tshirtRepository).findById(1);
        doReturn(tshirtList).when(tshirtRepository).findAll();
        doReturn(tshirtList).when(tshirtRepository).findByColor("gold");
        doReturn(tshirtList).when(tshirtRepository).findBySize("s");
    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("James Brown");
        invoice.setCity("New Haven");
        invoice.setState("CT");
        invoice.setZipCode("11111");
        invoice.setItemType("Games");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("99.99"));
        invoice.setSubtotal(new BigDecimal("99.99"));
        invoice.setTax(new BigDecimal("2.99"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("104.97"));

        Invoice invoice2 = new Invoice();
        invoice.setName("James Brown");
        invoice.setCity("New Haven");
        invoice.setState("CT");
        invoice.setZipCode("11111");
        invoice.setItemType("Games");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("99.99"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(invoiceList).when(invoiceRepository).findAll();
    }

    @Test
    public void findAllInvoices() {
        List<InvoiceViewModel> fromService = service.getAllInvoices();

        assertEquals(1, fromService.size());
    }

    @Test
    public void findAllGames() {
        List<Game> gameSearch = service.getAllGames();

        assertEquals(1, gameSearch.size());
    }

    @Test
    public void findAllGamesByStudio() {
        List<Game> studioSearch = service.getGamesByStudio("Studio");

        assertEquals(1, studioSearch.size());
    }

    @Test
    public void findAllGamesByEsrbRating() {
        List<Game> esrbSearch = service.getGamesByEsrbRating("Mature");

        assertEquals(1, esrbSearch.size());
    }

    @Test
    public void findAllGamesByTitle() {
        List<Game> titleSearch = service.getGamesByTitle("Game of Thrones X");

        assertEquals(1, titleSearch.size());
    }
    @Test
    public void findAllConsoles() {
        List<Console> consoleSearch = service.getAllConsoles();

        assertEquals(1, consoleSearch.size());
    }

    @Test
    public void findAllTshirts() {
        List<Game> gameSearch = service.getAllGames();

        assertEquals(1, gameSearch.size());
    }



    @Test
    public void saveFindGame() {
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Game of Thrones X");
        game.setEsrbRating("Mature");
        game.setDescription("New Characters!");
        game.setPrice(new BigDecimal("99.99"));
        game.setStudio("Studio");
        game.setQuantity(100);

        Game fromService = service.findGameById(game.getGameId());
        assertEquals(game, fromService);
    }

//    @Test
//    public void saveFindConsole() {
//        Console console1 = new Console();
//        console1.setModel("Switch");
//        console1.setManufacturer("Nintendo");
//        console1.setMemoryAmount("256gb");
//        console1.setProcessor("NotAGoodOne");
//        console1.setPrice(new BigDecimal("199.99"));
//        console1.setQuantity(5);
//        console1.setId(2);
//
//        Console fromService = service.getConsoleById(console1.getId());
//        assertEquals(console1, fromService);
//    }


}