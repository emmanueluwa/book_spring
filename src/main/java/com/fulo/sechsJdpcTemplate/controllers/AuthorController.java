package com.fulo.sechsJdpcTemplate.controllers;


import com.fulo.sechsJdpcTemplate.domain.dto.AuthorDto;
import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
import com.fulo.sechsJdpcTemplate.mappers.Mapper;
import com.fulo.sechsJdpcTemplate.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorMapper = authorMapper;
        this.authorService = authorService;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);

        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }
}
