package com.fulo.sechsJdpcTemplate.repositories;


import com.fulo.sechsJdpcTemplate.TestDataUtil;
import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
import com.fulo.sechsJdpcTemplate.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTests {

    private BookRepository underTest;
    private AuthorRepository authorRepository;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorEntity = authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorEntity = authorRepository.save(authorEntity);

        BookEntity bookA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookA);

        BookEntity bookB = TestDataUtil.createTestBookB(authorEntity);
        underTest.save(bookB);

        BookEntity bookC = TestDataUtil.createTestBookC(authorEntity);
        underTest.save(bookC);

        Iterable<BookEntity> results = underTest.findAll();
        assertThat(results)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorEntity = authorRepository.save(authorEntity);

        BookEntity bookA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookA);

        bookA.setTitle("KEEP GOING");
        underTest.save(bookA);

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorEntity = authorRepository.save(authorEntity);

        BookEntity bookA = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookA);

        underTest.deleteById(bookA.getIsbn());

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();

    }
}
