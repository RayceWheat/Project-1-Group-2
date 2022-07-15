
package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Console;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepo;

    public ConsoleRepositoryTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.consoleRepo.deleteAll();
    }

    @Test
    public void addGetDeleteConsole() {
        Console console1 = new Console();
        console1.setModel("Switch");
        console1.setManufacturer("Nintendo");
        console1.setMemoryAmount("256gb");
        console1.setProcessor("NotAGoodOne");
        console1.setPrice(new BigDecimal("299.99"));
        console1.setQuantity(5);
        this.consoleRepo.save(console1);
        Assert.assertEquals(true, this.consoleRepo.existsById(console1.getId()));
        this.consoleRepo.deleteById(console1.getId());
        Assert.assertEquals(false, this.consoleRepo.existsById(console1.getId()));
    }

    @Test
    public void getAllConsoles() {
        Console console1 = new Console();
        console1.setModel("Switch");
        console1.setManufacturer("Nintendo");
        console1.setMemoryAmount("256gb");
        console1.setProcessor("NotAGoodOne");
        console1.setPrice(new BigDecimal("299.99"));
        console1.setQuantity(5);
        Console console2 = new Console();
        console2.setModel("PlayStation5");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("300gb");
        console2.setProcessor("BigSuperGoodOne");
        console2.setPrice(new BigDecimal("399.99"));
        this.consoleRepo.save(console1);
        this.consoleRepo.save(console2);
        List<Console> allConsoles = this.consoleRepo.findAll();
        Assert.assertEquals(2L, (long)allConsoles.size());
    }

    @Test
    public void updateConsole() {
        Console console1 = new Console();
        console1.setModel("Switch");
        console1.setManufacturer("Nintendo");
        console1.setMemoryAmount("256gb");
        console1.setProcessor("NotAGoodOne");
        console1.setPrice(new BigDecimal("299.99"));
        console1.setQuantity(5);
        console1.setModel("Super Switch");
        Console updatedConsole = (Console)this.consoleRepo.save(console1);
        Assert.assertEquals(updatedConsole.getModel(), "Super Switch");
    }
}