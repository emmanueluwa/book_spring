package com.fulo.sechsJdpcTemplate.dao.Impl;

import com.fulo.sechsJdpcTemplate.TestDataUtil;
import com.fulo.sechsJdpcTemplate.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("384-3-4543-4567-0"),
                eq("The psychology of money"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        underTest.findOne("384-3-4543-4567-0");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id from books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("384-3-4543-4567-0")
        );
    }

    @Test
    public void testThatFindBookGeneratesCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id from books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Book book = TestDataUtil.createTestBookA();

        underTest.update("384-3-4543-4567-0", book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "384-3-4543-4567-0",
                "The psychology of money",
                1L,
                "384-3-4543-4567-0"
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        underTest.delete("384-3-4543-4567-0");

        verify(jdbcTemplate).update(
                "DELETE FROM books where isbn = ?",
                "384-3-4543-4567-0"
        );
    }
}
