package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Games;
import com.company.Group2GameStore.repository.GamesRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GamesController {

    @Autowired
    GamesRepository gamesRepository;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getAllGames(@RequestParam(required=false) String studio, @RequestParam(required=false) String esrbRating, @RequestParam(required=false) String title) {
        if (studio != null) {
            return gamesRepository.findGamesByStudio(studio);
        }
        if (esrbRating != null) {
            return gamesRepository.findGamesByEsrbRating(esrbRating);
        }
        if (title != null) {
            return gamesRepository.findGamesByTitle(title);
        }
        return gamesRepository.findAll();

    }

    // create game
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Games addGame (@RequestBody Games game) {

        return gamesRepository.save(game);
    }

    // find one game
    @RequestMapping(value="/games/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Games findGamesById(@PathVariable int id) {
        Optional<Games> game = gamesRepository.findById(id);
        // we use the optional type in case we get an empty response

        if (game.isPresent() == false) {
            throw new IllegalArgumentException("invalid id");
        }
        return game.get();
    }

    // delete game
    @RequestMapping(value="/games/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroyCustomer(@PathVariable int id) {
        gamesRepository.deleteById(id);
    }

    // update a game
    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int id, @RequestBody Games game) {
        if (game.getGameId() == null) {
            game.setGameId(id);
        } else if (game.getGameId() != id) {
            throw new IllegalArgumentException("Ids don't match.");
        }
        gamesRepository.save(game);
    }


}
