package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Console;
import com.company.Group2GameStore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping("/consoles")
    public List<Console> getConsoles() {
        return consoleRepository.findAll();
    }

//    @GetMapping("/consoles/{id}")
//    public Console getConsoleById(@PathVariable int id){
//        if (consoleRepository.findById(id) == null) {
//            return null;
//        }
//        return consoleRepository.findById(id).get();
//    }

    @GetMapping("/console/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer){
        List<Console> consoleByManufacturer = consoleRepository.findConsoleByManufacturer(manufacturer);

        return consoleByManufacturer;
    }



    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createNewConsole(@RequestBody Console console){
        return consoleRepository.save(console);
    }

    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console){
        consoleRepository.save(console);
    }

    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id){
        consoleRepository.deleteById(id);
    }

//
//    Consoles:
//
//    Perform standard CRUD operations for consoles.
//
//            Search for consoles by manufacturer.


}
