package com.bookstore.bookexplorer;

import com.bookstore.bookexplorer.model.Book;
import com.bookstore.bookexplorer.repository.BookRepository;
import com.bookstore.bookexplorer.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private static BookService bookService;

    // TODO: Unit tests for post updates to database

    @BeforeAll
    public static void setUp() {
        bookService = new BookService();
    }

    @Test
    public void searchISBN_noBookFound() throws Exception {
        // given(bookRepository.findByISBN(anyString())).willReturn(Optional.empty());

        // Optional<Book> book = bookService.getBookByISBN("");
        //assertThat(book.isPresent() == false);
    }
}
