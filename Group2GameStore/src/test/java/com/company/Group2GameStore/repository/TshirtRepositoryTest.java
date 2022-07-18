package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class TshirtRepositoryTest {

    @Autowired
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception{
        tshirtRepository.deleteAll();
    }

    @Test
    public void addGetDeleteTshirt() {
        Tshirt tshirt = new Tshirt();
//        tshirt.settShirtId(1);
        tshirt.setTshirt("League of Legends");
        tshirt.setColor("gold");
        tshirt.setSize("s");
        tshirt.setDescription("game merch");
        tshirt.setPrice(new BigDecimal(99.99));
        tshirt.setQuantity(100);

        tshirt = tshirtRepository.save(tshirt);

//        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.gettShirtId());

        //assert
        assertEquals(true, tshirtRepository.existsById(tshirt.gettShirtId()));

        tshirtRepository.deleteById(tshirt.gettShirtId());

        //check tshirt1 is no longer present
//        tshirt1 = tshirtRepository.findById(tshirt.gettShirtId());
//        assertFalse(tshirt1.isPresent());
        assertEquals(false, tshirtRepository.existsById(tshirt.gettShirtId()));
    }

    //GET all test
    @Test
    public void shouldReturnAllTshirts() {
        Tshirt tshirt1 = new Tshirt();
        tshirt1.settShirtId(1);
        tshirt1.setTshirt("League of Legends");
        tshirt1.setColor("gold");
        tshirt1.setSize("s");
        tshirt1.setDescription("game merch");
        tshirt1.setPrice(new BigDecimal(99.99));
        tshirt1.setQuantity(100);

        tshirtRepository.save(tshirt1);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.settShirtId(2);
        tshirt2.setTshirt("PUBG");
        tshirt2.setColor("silver");
        tshirt2.setSize("s");
        tshirt2.setDescription("game merch");
        tshirt2.setPrice(new BigDecimal(99.99));
        tshirt2.setQuantity(100);

        //second object doesn't need to be saved to variable
        tshirtRepository.save(tshirt2);

        List<Tshirt> list = tshirtRepository.findAll();
        assertEquals(2, list.size());
    }

    //update test
    @Test
    public void shouldUpdateTshirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.settShirtId(0);
        tshirt.setTshirt("League of Legends");
        tshirt.setColor("gold");
        tshirt.setSize("s");
        tshirt.setDescription("game merch");
        tshirt.setPrice(new BigDecimal(99.99));
        tshirt.setQuantity(100);

        tshirt.setTshirt("Pubg");
        Tshirt tshirtUpdate = tshirtRepository.save(tshirt);
//        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.gettShirtId());
        assertEquals(tshirtUpdate.getTshirt(), "Pubg");
    }
}