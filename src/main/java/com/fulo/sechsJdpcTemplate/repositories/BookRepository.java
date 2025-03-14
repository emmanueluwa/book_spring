package com.fulo.sechsJdpcTemplate.repositories;

import com.fulo.sechsJdpcTemplate.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository <BookEntity, String>,
        PagingAndSortingRepository<BookEntity, String> {

}
