package com.fulo.sechsJdpcTemplate;

import com.fulo.sechsJdpcTemplate.domain.dto.AuthorDto;
import com.fulo.sechsJdpcTemplate.domain.dto.BookDto;
import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
import com.fulo.sechsJdpcTemplate.domain.entities.BookEntity;

public final class TestDataUtil {

    private TestDataUtil() {

    }


    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder().name("Latifah Nisr").age(27).build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder().name("Furqan Lawah").age(29).build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder().name("Sasu Owei").age(63).build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("384-3-4543-4567-0")
                .title("The psychology of money")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("384-3-4543-4567-0")
                .title("The psychology of money")
                .author(null)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("389-3-4543-4568-0")
                .title("psychology of money II")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("387-3-4543-4565-0")
                .title("of money III")
                .authorEntity(authorEntity)
                .build();
    }
}
