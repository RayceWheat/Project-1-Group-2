package com.company.Group2GameStore.repository;


import com.company.Group2GameStore.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Games, Integer> {

    List<Games> findGamesByStudio(String studio);
    List<Games> findGamesByEsrbRating(String esrbRating);
    List<Games> findGamesByTitle(String title);

}
