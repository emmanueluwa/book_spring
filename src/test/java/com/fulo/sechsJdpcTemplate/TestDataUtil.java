package com.fulo.sechsJdpcTemplate;

import com.fulo.sechsJdpcTemplate.domain.Author;
import com.fulo.sechsJdpcTemplate.domain.Book;

public final class TestDataUtil {

    private TestDataUtil() {

    }


    public static Author createTestAuthorA() {
        return Author.builder().id(1L).name("Latifah Nisr").age(27).build();
    }

    public static Author createTestAuthorB() {
        return Author.builder().id(2L).name("Furqan Lawah").age(29).build();
    }

    public static Author createTestAuthorC() {
        return Author.builder().id(3L).name("Sasu Owei").age(63).build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("384-3-4543-4567-0")
                .title("The psychology of money")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("389-3-4543-4568-0")
                .title("psychology of money II")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("387-3-4543-4565-0")
                .title("of money III")
                .authorId(1L)
                .build();
    }
}
