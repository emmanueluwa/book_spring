package com.fulo.sechsJdpcTemplate.services;

import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
