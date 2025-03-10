package com.fulo.sechsJdpcTemplate.repositories;

import com.fulo.sechsJdpcTemplate.TestDataUtil;
import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
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
public class AuthorEntityRepositoryIntegrationTests {

    private AuthorRepository underTest;

    //message to spring -> inject dependencies as declared in constructor for dao
    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();

        underTest.save(authorEntity);

        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        underTest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
        underTest.save(authorEntityC);

        Iterable<AuthorEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorEntityA, authorEntityB, authorEntityC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntityA);
        authorEntityA.setName("Khadijah");
        underTest.save(authorEntityA);

        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntityA);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntityA);

        underTest.deleteById(authorEntityA.getId());

        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        underTest.save(testAuthorEntityA);
        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorEntityB);
        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = underTest.ageLessThan(28);
        assertThat(result).containsExactly(testAuthorEntityA);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        underTest.save(testAuthorEntityA);
        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorEntityB);
        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(28);
        assertThat(result).containsExactly(testAuthorEntityB, testAuthorEntityC);
    }
}
