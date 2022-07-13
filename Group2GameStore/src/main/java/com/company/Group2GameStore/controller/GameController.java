package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.repository.GameRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames(@RequestParam(required=false) String studio, @RequestParam(required=false) String esrbRating, @RequestParam(required=false) String title) {
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

    // create game
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame (@RequestBody Game game) {

        return gameRepository.save(game);
    }

    // find one game
    @RequestMapping(value="/games/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Game findGamesById(@PathVariable int id) {
        Optional<Game> game = gameRepository.findById(id);
        // we use the optional type in case we get an empty response

        if (game.isPresent() == false) {
            throw new IllegalArgumentException("invalid id");
        }
        return game.get();
    }


    // update a game
    @RequestMapping(value = "/games", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateGame(@RequestBody Game game) {
        gameRepository.save(game);
    }

    // delete game
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteGame(@PathVariable int id) {
        gameRepository.deleteById(id);
    }

}
