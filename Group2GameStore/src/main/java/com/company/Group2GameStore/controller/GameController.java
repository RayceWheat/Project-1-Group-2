package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Game;
import com.company.Group2GameStore.repository.GameRepository;
import com.company.Group2GameStore.service.ServiceLayer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return serviceLayer.getAllGames();
    }

    @RequestMapping(value = "/games/title", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByTitle(String title) {
        return serviceLayer.getGamesByTitle(title);
    }

    @RequestMapping(value = "/games/esrbRating", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrbRating(String esrbRating) {
        return serviceLayer.getGamesByEsrbRating(esrbRating);
    }

    @RequestMapping(value = "/games/studio", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(String studio) {
        return serviceLayer.getGamesByTitle(studio);
    }

    // create game
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame (@RequestBody @Valid Game game) {
        return serviceLayer.addGame(game);
    }

    // find one game
    @RequestMapping(value="/games/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Game findGamesById(@PathVariable int id) {
        return serviceLayer.findGameById(id);
    }


    // update a game
    @RequestMapping(value = "/games", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void updateGame(@RequestBody @Valid Game game) {
        serviceLayer.updateGame(game);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Game updateGameById(@PathVariable int id, @RequestBody @Valid Game game){
        return serviceLayer.updateGameById(id, game);
    }


    // delete game
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameById(@PathVariable int id) {
        serviceLayer.deleteGameById(id);
    }

}
