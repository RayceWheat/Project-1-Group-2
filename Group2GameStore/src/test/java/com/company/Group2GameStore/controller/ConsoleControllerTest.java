//package com.company.Group2GameStore.controller;
//
//import com.company.Group2GameStore.model.Console;
//import com.company.Group2GameStore.repository.ConsoleRepository;
//import com.company.Group2GameStore.service.ServiceLayer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.doReturn;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ConsoleController.class)
//public class ConsoleControllerTest {
//
//    //Wiring the MockMvc object
//    @Autowired
//    private MockMvc mockMvc;
//
//    //This will change to service layer when we get to that
//    @MockBean
//    ServiceLayer repo;
//
//    //Object mapper used to convert java objects to Json and vice versa
//    private ObjectMapper mapper = new ObjectMapper();
//
//    private Console console;
//
//    private String consoleJson;
//
//    private List<Console> allConsoles = new ArrayList<>();
//
//    private String allConsoleJson;
//
//    @Before
//    public void setup() throws Exception{
//        console = new Console();
//        console.setModel("PlayStation5");
//        console.setManufacturer("Sony");
//        console.setMemoryAmount("300gb");
//        console.setProcessor("BigSuperGoodOne");
//        console.setPrice(new BigDecimal("399.99"));
//        console.setQuantity(10);
//        console.setId(11);
//        consoleJson = mapper.writeValueAsString(console);
//
//        Console console1 = new Console();
//        console1.setModel("Switch");
//        console1.setManufacturer("Nintendo");
//        console1.setMemoryAmount("256gb");
//        console1.setProcessor("NotAGoodOne");
//        console1.setPrice(new BigDecimal("299.99"));
//        console1.setQuantity(5);
//        console1.setId(2);
//
//        allConsoles.add(console);
//        allConsoles.add(console1);
//        allConsoleJson = mapper.writeValueAsString(allConsoles);
//    }
//
//    @Test
//    public void shouldCreateNewConsoleOnPostRequest() throws Exception{
//        Console inputConsole = new Console();
//        inputConsole.setManufacturer("Sega");
//        inputConsole.setModel("Gensis");
//        inputConsole.setProcessor("Old");
//        inputConsole.setQuantity(100);
//        inputConsole.setMemoryAmount("32bits");
//
//        String inputJson = mapper.writeValueAsString(inputConsole);
//
//        doReturn(console).when(repo).save(inputConsole);
//
//        mockMvc.perform(
//                post("/consoles")
//                    .content(inputJson)
//                    .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(content().json(consoleJson));
//    }
//
//    @Test
//    public void shouldReturn422StatusCodeWithInvalidRequestBody() throws Exception {
//        Console badConsole = new Console();
//
//        String inputJson = mapper.writeValueAsString(badConsole);
//
//        mockMvc.perform(
//                post("/consoles")
//                        .content(inputJson)
//                        .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    public void shouldReturn422StatusCodeIfIdsDoNotMatch() throws Exception{
//        Console console1 = new Console();
//        console1.setModel("Switch");
//        console1.setManufacturer("Nintendo");
//        console1.setMemoryAmount("256gb");
//        console1.setProcessor("NotAGoodOne");
//        console1.setPrice(new BigDecimal("199.99"));
//        console1.setQuantity(5);
//        console1.setId(2);
//
//        Optional<Console> optConsole = Optional.of(console1);
//        doReturn(optConsole).when(repo).findById(2);
//
//        String inputJson = mapper.writeValueAsString(console1);
//
//        mockMvc.perform(
//                        put("/consoles/5")
//                                .content(inputJson)
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    public void shouldReturnConsoleById() throws Exception{
//        Optional<Console> optConsole = Optional.of(console);
//        doReturn(optConsole).when(repo).findById(11);
//
//        mockMvc.perform(
//                get("/consoles/11"))
//                .andExpect(status().isOk())
//                .andExpect((content().json(consoleJson))
//        );
//
//    }
//
//
//    @Test
//    public void shouldReturnAllConsolesByManufacturer() throws Exception {
//        doReturn(allConsoles).when(repo).findConsoleByManufacturer("Sony");
//
//        mockMvc.perform(
//                get("/consoles/manufacturer/Sony"))
//                .andExpect(status().isOk())
//                .andExpect((content().json(allConsoleJson))
//        );
//    }
//
////    @Test
////    public void shouldReturn422IfManufacturerIsNotThere() throws Exception{
////        mockMvc.perform(
////                        get("/consoles/manufacturer/Harley"))
////                .andExpect(status().isUnprocessableEntity());
////
////    }
//
//    @Test
//    public void shouldReturn404StatusCodeIfConsoleNotFound() throws Exception{
//        mockMvc.perform(get("/consoles/-1"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//
//    @Test
//    public void shouldUpdateConsoleAndReturnStatus204() throws Exception{
//        mockMvc.perform(
//                put("/consoles")
//                        .content(consoleJson)
//                        .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void shouldUpdateConsoleByIdAndReturn204StatusCode() throws Exception {
//        Console console1 = new Console();
//        console1.setModel("Switch");
//        console1.setManufacturer("Nintendo");
//        console1.setMemoryAmount("256gb");
//        console1.setProcessor("NotAGoodOne");
//        console1.setPrice(new BigDecimal("199.99"));
//        console1.setQuantity(5);
//        console1.setId(2);
//
//        Optional<Console> optConsole = Optional.of(console1);
//        doReturn(optConsole).when(repo).findById(2);
//
//        String inputJson = mapper.writeValueAsString(console1);
//
//        mockMvc.perform(
//                put("/consoles/2")
//                        .content(inputJson)
//                        .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//        mockMvc.perform(
//                get("/consoles/2")
//                        .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andDo(print())
//                .andExpect(content().json(inputJson));
//    }
//
//    @Test
//    public void shouldDeleteByIdAndReturnStatus204() throws Exception{
//        mockMvc.perform(delete("/consoles/11")).andExpect(status().isNoContent());
//    }
//
//}