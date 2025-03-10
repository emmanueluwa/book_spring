package com.fulo.sechsJdpcTemplate.services.impl;

import com.fulo.sechsJdpcTemplate.domain.dto.BookDto;
import com.fulo.sechsJdpcTemplate.domain.entities.BookEntity;
import com.fulo.sechsJdpcTemplate.repositories.BookRepository;
import com.fulo.sechsJdpcTemplate.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(
                bookRepository
                        .findAll()
                        .spliterator(), false
        ).collect(Collectors.toList());
    }
}
