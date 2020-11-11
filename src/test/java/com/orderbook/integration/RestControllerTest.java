package com.orderbook.integration;

import com.orderbook.model.BuySell;
import com.orderbook.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.orderbook.model.Status;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("local-test")
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestControllerTest {

    private XmlMapper xmlMapper;

    @Autowired
    private MockMvc mvc;

    @Value("${server.port}")
    private String port;

    @BeforeEach
    public void init() {
        xmlMapper = new XmlMapper();
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        //create new Order
        com.orderbook.model.Order order = new Order(1L, UUID.randomUUID(), Status.OPEN, 1L, "USD", "EUR", new BigDecimal(10000), 1.4f, BuySell.BUY, "tester", LocalDate.of(2020, 11, 10));
        //serialize Order object to xml string
        String orderAsXml = serialize(order);
        //save new order
        String orderSaved = mvc.perform(post("/orders/save")
               .contentType(MediaType.APPLICATION_XML)
                .content(orderAsXml))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        //deserialize from xml string
        order = deserialize(orderSaved);

        //delete new order
        mvc.perform(delete("/orders/delete/".concat(order.getId().toString())))
                .andExpect(status().isOk());
        Assertions.assertTrue(order.getBuySell().name().equals(BuySell.BUY.name()));
    }

    @Test
    public void testFindAll() {
        try {
            mvc.perform(get("/orders"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String serialize(com.orderbook.model.Order order) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(order);
    }

    private com.orderbook.model.Order deserialize(String xml) throws JsonProcessingException {
        return xmlMapper.readValue(xml, Order.class);
    }
}
