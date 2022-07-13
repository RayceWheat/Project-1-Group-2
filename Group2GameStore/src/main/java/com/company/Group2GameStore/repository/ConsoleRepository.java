package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    List<Console> findConsoleByManufacturer(String manufacturer);

}
