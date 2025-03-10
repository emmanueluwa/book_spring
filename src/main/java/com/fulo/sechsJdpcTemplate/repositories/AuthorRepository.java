package com.fulo.sechsJdpcTemplate.repositories;

import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//author repository now a bean that can be injected anywhere as needed
@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    Iterable<AuthorEntity> ageLessThan(int age);

    @Query("SELECT a from AuthorEntity a where a.age > ?1")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
