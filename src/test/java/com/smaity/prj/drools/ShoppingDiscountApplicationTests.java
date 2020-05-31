package com.smaity.prj.drools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smaity.prj.drools.controller.ShoppingController;
import com.smaity.prj.drools.domain.CartBill;
import com.smaity.prj.drools.domain.CustomerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShoppingDiscountApplicationTests {

    static {
        Path ruleConfigDirectory = Paths.get("src/config");
        System.setProperty("service.rule.location", ruleConfigDirectory.toAbsolutePath().toString());
    }

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShoppingController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testRegularOffer1() throws Exception {
        CartBill cartBill = new CartBill();
        cartBill.setCartId(1L);
        cartBill.setBaseAmount(BigDecimal.valueOf(15000));
        cartBill.setCustomerType(CustomerType.REGULAR);
        cartBill.setDiscountedAmount(BigDecimal.valueOf(0));

        ObjectMapper mapper = new ObjectMapper();
        String cartInput = mapper.writeValueAsString(cartBill);

        this.mockMvc.perform(post("/getChristmasDiscountedBill")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(cartInput))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"cartId\": 1,\n" +
                        "    \"totalAmount\": 13500\n" +
                        "}"));
    }

    @Test
    public void testPremiumOffer1() throws Exception {
        CartBill cartBill = new CartBill();
        cartBill.setCartId(1L);
        cartBill.setBaseAmount(BigDecimal.valueOf(20000));
        cartBill.setCustomerType(CustomerType.PREMIUM);
        cartBill.setDiscountedAmount(BigDecimal.valueOf(0));

        ObjectMapper mapper = new ObjectMapper();
        String cartInput = mapper.writeValueAsString(cartBill);

        this.mockMvc.perform(post("/getChristmasDiscountedBill")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(cartInput))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"cartId\": 1,\n" +
                        "    \"totalAmount\": 15800\n" +
                        "}"));
    }
}
