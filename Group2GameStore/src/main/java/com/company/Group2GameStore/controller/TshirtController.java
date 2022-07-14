package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Tshirt;
import com.company.Group2GameStore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {
    @Autowired
    TshirtRepository tshirtRepository;
//    private static int idCounter = 1;

    //Create Tshirt
    @RequestMapping(value = "/tshirt", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt createTshirt(@RequestBody @Valid Tshirt tshirt) { return tshirtRepository.save(tshirt);}


    //Find all Tshirts, filter by color/size
    @RequestMapping(value="/tshirt", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirts(@RequestParam(required = false) String color, String size) {
        if (color != null) {
            return tshirtRepository.findByColor(color);
        }
        if (size != null) {
            return tshirtRepository.findBySize(size);
        }
//        if (color != null && size != null) {
//            return tshirtRepository.findByColorAndSize(color, size);
//        }
        return tshirtRepository.findAll();
    }

    //Find by id
    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int id) {
        Optional<Tshirt> tshirt = tshirtRepository.findById(id);
        if(tshirt.isPresent() == false) {
            throw new IllegalArgumentException("Invalid Id");
        }

        return tshirt.get();
    }


//    Update by id
    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirtById(@PathVariable int id, @RequestBody Tshirt tshirt) {
        if (tshirt.gettShirtId() == null) {
            tshirt.settShirtId(id);
        } else if (tshirt.gettShirtId() != id) {
            throw new IllegalArgumentException("Id does not match");
        }

        tshirtRepository.save(tshirt);
    }


    //Delete by id
    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id) { tshirtRepository.deleteById(id);}

}
