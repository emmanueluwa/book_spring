package com.fulo.sechsJdpcTemplate.dao;

import com.fulo.sechsJdpcTemplate.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();

    void update(String isbn, Book book);
}
