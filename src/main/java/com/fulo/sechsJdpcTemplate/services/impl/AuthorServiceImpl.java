package com.fulo.sechsJdpcTemplate.services.impl;

import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
import com.fulo.sechsJdpcTemplate.repositories.AuthorRepository;
import com.fulo.sechsJdpcTemplate.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
