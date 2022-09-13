package com.example.cinema;

import com.example.cinema.controller.MovieController;
import com.example.cinema.domain.dto.movie.CreateMovieDTO;
import com.example.cinema.domain.dto.movie.SearchMovieDTO;
import com.example.cinema.domain.dto.movie.UpdateMovieDTO;
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
class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieController movieController;

    @Test
    void contextLoads() {
        assertThat(movieController).isNotNull();
    }

    @Test
    public void createAndGetEntityTest() throws Exception {
        CreateMovieDTO createMovieDTO = new CreateMovieDTO("Titanic2", "new part of Titanic", "drama");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(createMovieDTO);

        mockMvc.perform(post("/api/movie")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/movie/1")).andExpect(status().isOk());
    }

    @Test
    public void searchBySpecificationTest() throws Exception {
        SearchMovieDTO searchMovieDTO = new SearchMovieDTO("Titanic", "some description3", "drama");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(searchMovieDTO);

        mockMvc.perform(get("/api/movie/search-by-specification")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

    @Test
    public void updateAndGetEntityTest() throws Exception {
        UpdateMovieDTO updateMovieDTO = new UpdateMovieDTO("Titanic", "new", "drama");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(updateMovieDTO);

        mockMvc.perform(put("/api/movie/3")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

}
