package com.fulo.sechsJdpcTemplate.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulo.sechsJdpcTemplate.TestDataUtil;
import com.fulo.sechsJdpcTemplate.domain.dto.AuthorDto;
import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
import com.fulo.sechsJdpcTemplate.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

    private AuthorService authorService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);

        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);

        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Latifah Nisr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("27")
        );
    }

    @Test
    public void testThatListAuthorReturnsHttpStatus200() throws Exception {
        mockMvc.perform (
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListAuthorReturnsListOfAuthors() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform (
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Latifah Nisr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value("27")
        );
    }

    @Test
    public void testThatListAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform (
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListAuthorReturnsHttpStatus404WhenAuthorDoesNotExist() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform (
                MockMvcRequestBuilders.get("/authors/102")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatListAuthorReturnsAuthorWhenAuthorExists() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform (
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Latifah Nisr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("27")
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenAuthorDoesNotExist() throws Exception {
        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform (
                MockMvcRequestBuilders.put("/authors/102")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200OkWhenAuthorExists() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorA = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform (
                MockMvcRequestBuilders.put("/authors/"+savedAuthorA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
        AuthorEntity savedAuthorDto = authorService.save(authorDto);
        authorDto.setId(savedAuthor.getId());

        String authorDtoUpdateJson = objectMapper.writeValueAsString(savedAuthorDto);

        mockMvc.perform (
                MockMvcRequestBuilders.put("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorDto.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(savedAuthorDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(savedAuthorDto.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus200Ok() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorA = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("Muhaj");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform (
                MockMvcRequestBuilders.patch("/authors/"+savedAuthorA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Muhaj")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDtoA.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorA = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("Muhaj");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform (
                MockMvcRequestBuilders.patch("/authors/"+savedAuthorA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
