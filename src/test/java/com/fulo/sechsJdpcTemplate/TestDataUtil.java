package com.fulo.sechsJdpcTemplate;

import com.fulo.sechsJdpcTemplate.domain.Author;
import com.fulo.sechsJdpcTemplate.domain.Book;

public final class TestDataUtil {

    private TestDataUtil() {

    }


    public static Author createTestAuthorA() {
        return Author.builder().name("Latifah Nisr").age(27).build();
    }

    public static Author createTestAuthorB() {
        return Author.builder().name("Furqan Lawah").age(29).build();
    }

    public static Author createTestAuthorC() {
        return Author.builder().name("Sasu Owei").age(63).build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("384-3-4543-4567-0")
                .title("The psychology of money")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("389-3-4543-4568-0")
                .title("psychology of money II")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("387-3-4543-4565-0")
                .title("of money III")
                .author(author)
                .build();
    }
}
