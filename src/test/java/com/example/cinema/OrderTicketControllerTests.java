package com.example.cinema;

import com.example.cinema.controller.OrderTicketController;
import com.example.cinema.domain.dto.order.CreateOrderTicketDTO;
import com.example.cinema.domain.dto.order.SearchOrderTicketDTO;
import com.example.cinema.domain.dto.order.UpdateOrderTicketDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-integrationtest.properties")
@Sql(value = {"/create-movies.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class OrderTicketControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderTicketController orderTicketController;

    @Test
    void contextLoads() {
        assertThat(orderTicketController).isNotNull();
    }

    @Test
    public void createAndGetEntityTest() throws Exception {
        CreateOrderTicketDTO createOrderTicketDTO = new CreateOrderTicketDTO(5, 8, 100.00, 1L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(createOrderTicketDTO);

        mockMvc.perform(post("/api/order")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/order/1")).andExpect(status().isOk());
    }

    @Test
    public void searchByCriteriaTest() throws Exception {
        SearchOrderTicketDTO searchOrderTicketDTO = new SearchOrderTicketDTO(5, 8, 100.00, 120.00, 1L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(searchOrderTicketDTO);

        mockMvc.perform(get("/api/order/search-by-criteria")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateAndGetEntityTest() throws Exception {
        UpdateOrderTicketDTO updateOrderTicketDTO = new UpdateOrderTicketDTO(5, 8, 100.00, 1L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(updateOrderTicketDTO);

        mockMvc.perform(put("/api/order/3")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

}
