package com.company.Group2GameStore.repository;


import com.company.Group2GameStore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findGamesByStudio(String studio);
    List<Game> findGamesByEsrbRating(String esrbRating);
    List<Game> findGamesByTitle(String title);

}
