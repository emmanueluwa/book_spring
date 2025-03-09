package com.fulo.sechsJdpcTemplate.repositories;

import com.fulo.sechsJdpcTemplate.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//author repository now a bean that can be injected anywhere as needed
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
