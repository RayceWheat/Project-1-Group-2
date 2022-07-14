package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Game;
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
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepo;

    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();
    }

    @Test
    public void addGetDeleteGame() {
        Game newGame = new Game();
        newGame.setTitle("Game of Thrones");
        newGame.setEsrbRating("Mature");
        newGame.setDescription("Awesome Game with numerous alternate endings.");
        newGame.setPrice(new BigDecimal("14.99"));
        newGame.setStudio("Warner Bros Entertainment");
        newGame.setQuantity(25);

        gameRepo.save(newGame);
        assertEquals(true, gameRepo.existsById(newGame.getGameId()));

        gameRepo.deleteById(newGame.getGameId());
        assertEquals(false, gameRepo.existsById(newGame.getGameId()));
    }

    @Test
    public void getAllGames() {
        Game newGame = new Game();
        newGame.setGameId(0);
        newGame.setTitle("Game of Thrones");
        newGame.setEsrbRating("Mature");
        newGame.setDescription("Awesome Game with numerous alternate endings.");
        newGame.setPrice(new BigDecimal("14.99"));
        newGame.setStudio("Warner Bros Entertainment");
        newGame.setQuantity(25);

        Game otherGame = new Game();
        otherGame.setGameId(0);
        otherGame.setTitle("Something With Elves");
        otherGame.setEsrbRating("Teen");
        otherGame.setDescription("Elves can fly and climb trees.");
        otherGame.setPrice(new BigDecimal("29.99"));
        otherGame.setStudio("Elf Lovers");
        otherGame.setQuantity(25);

        gameRepo.save(newGame);
        gameRepo.save(otherGame);

        List<Game> allGames = gameRepo.findAll();
        assertEquals(2, allGames.size());
    }

    @Test
    public void updateGame() {
        Game newGame = new Game();
        newGame.setGameId(0);
        newGame.setTitle("Game of Thrones");
        newGame.setEsrbRating("Mature");
        newGame.setDescription("Awesome Game with numerous alternate endings.");
        newGame.setPrice(new BigDecimal("14.99"));
        newGame.setStudio("Warner Bros Entertainment");
        newGame.setQuantity(25);

        newGame.setTitle("Game of Thrones: Dragons");

        Game updatedGame = gameRepo.save(newGame);
        assertEquals(updatedGame.getTitle(), "Game of Thrones: Dragons");
    }

}