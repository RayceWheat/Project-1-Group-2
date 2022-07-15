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
    public List<Game> getAllGames(@RequestParam(required=false) String studio, @RequestParam(required=false) String esrbRating, @RequestParam(required=false) String title) {
        return serviceLayer.getAllGames(studio, esrbRating, title);
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
    @ResponseStatus(HttpStatus.OK)

    public void updateGame(@RequestBody @Valid Game game) {
        serviceLayer.updateGame(game);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Game updateGameById(@PathVariable int id, @RequestBody @Valid Game game){
        return serviceLayer.updateGameById(id, game);
    }


    // delete game
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteGameById(@PathVariable int id) {
        serviceLayer.deleteGameById(id);
    }

}
