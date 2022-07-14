package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Console;
import com.company.Group2GameStore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    @RequestMapping(value = "/consoles", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return consoleRepository.findAll();
    }

    @GetMapping("/consoles/{id}")
    public Console getConsoleById(@PathVariable int id){
        Optional<Console> returnVal = consoleRepository.findById(id);

        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }

    }

    @GetMapping("/consoles/manufacturer/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer){
//        List<Console> consoleByManufacturer = consoleRepository.findConsoleByManufacturer(manufacturer);
//
//        return consoleByManufacturer;

        return consoleRepository.findConsoleByManufacturer(manufacturer);

    }

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createNewConsole(@RequestBody @Valid Console console){
        return consoleRepository.save(console);
    }

    @PutMapping("/consoles")
    public void updateConsole(@RequestBody @Valid Console console){
        consoleRepository.save(console);
    }

    @PutMapping("/consoles/{id}")
    public Console updateConsoleById(@PathVariable int id, @RequestBody @Valid Console console){
        if (console.getId() == id){
            return consoleRepository.save(console);
        }
        return null;
    }

    @DeleteMapping("/consoles/{id}")
    public void deleteConsoleById(@PathVariable int id){
        consoleRepository.deleteById(id);
    }


}
