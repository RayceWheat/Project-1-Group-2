//package com.company.Group2GameStore.controller;
//
//import com.company.Group2GameStore.model.Tshirt;
//import com.company.Group2GameStore.repository.TshirtRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.doReturn;
//
////for calling requests in .perform(), like .post() method
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(TshirtController.class)
//public class TshirtControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    //from RSVP service project example
//    @MockBean
//    TshirtRepository repo;//don't need private like Rsvp example
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    //
//    private Tshirt tshirt;
//    private String tshirtJson;
//    private List<Tshirt> allTshirts = new ArrayList<>();
//    private String allTshirtsJson;
//
//    @Before // if missing annotation, gets string is null for tshirtJson
//    public void setUp() throws Exception {
//        tshirt = new Tshirt();
//        tshirt.settShirtId(1);
//        tshirt.setTshirt("League of Legends");
//        tshirt.setColor("gold");
//        tshirt.setSize("s");
//        tshirt.setDescription("game merch");
//        tshirt.setPrice(new BigDecimal(99.99));
//        tshirt.setQuantity(100);
//        tshirtJson = mapper.writeValueAsString(tshirt);
//
//        Tshirt tshirt2 = new Tshirt();
//        tshirt2.setTshirt("PUBG");
//        tshirt2.setColor("camouflage");
//        tshirt2.setSize("s");
//        tshirt2.setDescription("game merch");
//        tshirt2.setPrice(new BigDecimal(99.99));
//        tshirt2.setQuantity(200);
////        Tshirt tshirt = new Tshirt(
////                2, "PUBG", "camouflage",
////                "s","Game merch",
////                new BigDecimal(99.99), 200);
//
//        allTshirts.add(tshirt);
//        allTshirts.add(tshirt2);
//        allTshirtsJson = mapper.writeValueAsString(allTshirts);
//    }
//
//    @Test
//    public void shouldReturnNewlyCreatedTshirt() throws Exception {
//        Tshirt inputTshirt = new Tshirt();
//        inputTshirt.settShirtId(1);
//        inputTshirt.setTshirt("League of Legends");
//        inputTshirt.setColor("gold");
//        inputTshirt.setSize("s");
//        inputTshirt.setDescription("game merch");
//        inputTshirt.setPrice(new BigDecimal(99.99));
//        inputTshirt.setQuantity(100);
//        String inputJson = mapper.writeValueAsString(inputTshirt);
//
//        doReturn(tshirt).when(repo).save(inputTshirt);
//
//        mockMvc.perform(
//                        post("/tshirt")
//                                .content(inputJson)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(content().json(tshirtJson));
//    }
//
//    @Test
//    public void shouldReturnAllTshirtsByColor() throws Exception {
//        doReturn(allTshirts).when(repo).findByColor("gold");
//        mockMvc.perform(get("/tshirt?color=gold"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(allTshirtsJson));
//    }
//
//    @Test
//    public void shouldReturnAllTshirtsBySize() throws Exception {
//        doReturn(allTshirts).when(repo).findBySize("s");
//        mockMvc.perform(get("/tshirt?size=s"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(allTshirtsJson));
//    }
//
//    @Test
//    public void shouldReturnTshirtById() throws Exception {
//        doReturn(Optional.of(tshirt)).when(repo).findById(1);
//        ResultActions result = mockMvc.perform(get("/tshirt/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(tshirtJson));
//    }
//
//    @Test
//    public void shouldUpdateById() throws Exception {
//        mockMvc.perform(
//                        put("/tshirt/1")
//                                .content(tshirtJson)
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isNoContent());//don't need to be like rsvp example (isOk)
//    }
//
//    @Test
//    public void shouldDeleteById() throws Exception {
//        mockMvc.perform(delete("/tshirt/1"))
//                .andExpect(status().isNoContent());
//    }
//}