package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Tshirt;
import com.company.Group2GameStore.repository.TshirtRepository;
import com.company.Group2GameStore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {
    @Autowired
    ServiceLayer serviceLayer;
//    private static int idCounter = 1;

    //Create Tshirt
    @RequestMapping(value = "/tshirt", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)

    public Tshirt createTshirt(@RequestBody Tshirt tshirt) {
        return serviceLayer.createTShirt(tshirt);
    }



    //Find all Tshirts, filter by color/size
    @RequestMapping(value="/tshirt", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirts(@RequestParam(required = false) String color, String size) {
        return serviceLayer.getAllTShirts(color, size);
    }

    //Find by id
    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int id) {
        return serviceLayer.getTshirtById(id);
    }


//    Update by id
    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirtById(@PathVariable int id, @RequestBody Tshirt tshirt) {
        serviceLayer.updateTshirtById(id, tshirt);
    }

    //Delete by id
    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id) { serviceLayer.deleteTshirtById(id);}

}
