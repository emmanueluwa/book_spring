package com.fulo.sechsJdpcTemplate.services;

import com.fulo.sechsJdpcTemplate.domain.dto.BookDto;
import com.fulo.sechsJdpcTemplate.domain.entities.BookEntity;

import java.util.List;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> findAll();
}
