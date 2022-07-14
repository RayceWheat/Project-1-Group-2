package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.exceptions.NotFoundException;
import com.company.Group2GameStore.model.Console;
import com.company.Group2GameStore.repository.ConsoleRepository;
import com.company.Group2GameStore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/consoles", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return serviceLayer.getAllConsoles();
    }

    @GetMapping("/consoles/{id}")
    public Console getConsoleById(@PathVariable int id){
        return serviceLayer.getConsoleById(id);
    }

    @GetMapping("/consoles/manufacturer/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer){
        return serviceLayer.getConsoleByManufacturer(manufacturer);
    }

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createNewConsole(@RequestBody @Valid Console console){
        return serviceLayer.createNewConsole(console);
    }

    @PutMapping("/consoles")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid Console console){
        serviceLayer.updateConsole(console);
    }

    @PutMapping("/consoles/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Console updateConsoleById(@PathVariable int id, @RequestBody @Valid Console console){
        return serviceLayer.updateConsoleById(id, console);
    }

    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteConsoleById(@PathVariable int id){
        serviceLayer.deleteConsoleById(id);
    }


}
