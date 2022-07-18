package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Console;
import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.repository.GameRepository;
import com.company.Group2GameStore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    ObjectMapper mapper = new ObjectMapper();

    private Game game;
    private String gameJson;
    private List<Game> allGames = new ArrayList<>();
    private String allGamesJson;

    @Before
    public void setup() throws Exception {
        game = new Game();
        game.setGameId(1);
        game.setTitle("Game of Thrones");
        game.setEsrbRating("Mature");
        game.setDescription("Awesome Game with numerous alternate endings.");
        game.setPrice(new BigDecimal("14.99"));
        game.setStudio("Warner Bros Entertainment");
        game.setQuantity(25);

        gameJson = mapper.writeValueAsString(game);

        Game otherGame = new Game();
        otherGame.setGameId(2);
        otherGame.setTitle("Something With Elves");
        otherGame.setEsrbRating("Teen");
        otherGame.setDescription("Elves can fly and climb trees.");
        otherGame.setPrice(new BigDecimal("29.99"));
        otherGame.setStudio("Elf Lovers");
        otherGame.setQuantity(25);

        allGames.add(game);
        allGames.add(otherGame);

        allGamesJson = mapper.writeValueAsString(allGames);

    }


    @Test
    public void shouldCreateNewGameOnPostRequest() throws Exception {

        Game inputGame = new Game();
        inputGame.setTitle("Game of Thrones");
        inputGame.setEsrbRating("Mature");
        inputGame.setDescription("Awesome Game with numerous alternate endings.");
        inputGame.setPrice(new BigDecimal("14.99"));
        inputGame.setStudio("Warner Bros Entertainment");
        inputGame.setQuantity(25);

        String inputJson = mapper.writeValueAsString(inputGame);

        doReturn(game).when(serviceLayer).addGame(inputGame);



        mockMvc.perform(
                post("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(gameJson));
    }

    @Test
    public void shouldReturnGameById() throws Exception {
        Optional<Game> optGame = Optional.of(game);
        doReturn(game).when(serviceLayer).findGameById(1);

        mockMvc.perform(
                        get("/games/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(gameJson))
                );
    }

    @Test
    public void shouldReturnAllGames() throws Exception {
        doReturn(allGames).when(serviceLayer).getAllGames();

        mockMvc.perform(
                        get("/games"))
                .andExpect(status().isOk())
                .andExpect(content().json(allGamesJson)
                );
    }

    @Test
    public void shouldReturnAllGamesByTitle() throws Exception {
        doReturn(allGames).when(serviceLayer).getGamesByTitle("Game of Thrones");

        mockMvc.perform(
                        get("/games/title/Game of Thrones"))
                .andExpect(status().isOk())
                .andExpect(content().json(allGamesJson)
                );
    }

    @Test
    public void shouldReturnAllGamesByEsrbRating() throws Exception {
        doReturn(allGames).when(serviceLayer).getGamesByEsrbRating("Teen");

        mockMvc.perform(
                        get("/games/esrbRating/Teen"))
                .andExpect(status().isOk())
                .andExpect(content().json(allGamesJson)
                );
    }

    @Test
    public void shouldReturnAllGamesByStudio() throws Exception {
        doReturn(allGames).when(serviceLayer).getGamesByStudio("Warner");

        mockMvc.perform(
                        get("/games/studio/Warner"))
                .andExpect(status().isOk())
                .andExpect(content().json(allGamesJson)
                );
    }

    @Test
    public void shouldUpdateGameAndReturnStatus204() throws Exception {
        mockMvc.perform(
                        put("/games/1")
                                .content(gameJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteGameByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/games/2")).andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn422StatusCodeIfOnePropertyNullOrEmpty() throws Exception {

        Game inputGame = new Game();
        inputGame.setTitle("Game of Thrones");
        inputGame.setEsrbRating("Mature");
        inputGame.setDescription("Awesome Game with numerous alternate endings.");
        inputGame.setPrice(new BigDecimal("14.99"));
        inputGame.setStudio("Warner Bros Entertainment");
        // inputGame.setQuantity is missing (null) - triggers 422 because validation set to NOTNULL

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        post("/games")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeIfGameNotFound() throws Exception {
        mockMvc.perform(get("/games/Tennis"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}