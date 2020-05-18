package com.bookstore.bookexplorer;

import com.bookstore.bookexplorer.controller.BookStoreController;
import com.bookstore.bookexplorer.exception.BookNotFoundException;
import com.bookstore.bookexplorer.model.Book;
import com.bookstore.bookexplorer.model.Post;
import com.bookstore.bookexplorer.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookStoreController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void searchISBN_bookNotFound() throws Exception {
        given(bookService.getBookByISBN(anyString())).willReturn(Optional.empty());

        // return 404 if no ISBN provided
        mockMvc.perform(MockMvcRequestBuilders.get("/get-book-by-isbn"))
                .andExpect(status().isNotFound());

        // return 404 if dummy ISBN provided
        mockMvc.perform(MockMvcRequestBuilders.get("/get-book-by-isbn?isbn=1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchTitle_bookNotFound() throws Exception {
        given(bookService.getBooksByTitle(anyString())).willReturn(new ArrayList<Book>());

        // return 404 if no tile provided
        mockMvc.perform(MockMvcRequestBuilders.get("/get-books-by-title"))
                .andExpect(status().isNotFound());

        // return 404 if dummy title provided
        mockMvc.perform(MockMvcRequestBuilders.get("/get-books-by-title?title=a"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchAuthor_bookNotFound() throws Exception {
        given(bookService.getBooksByAuthor(anyString())).willReturn(new ArrayList<Book>());

        // return 404 if no tile provided
        mockMvc.perform(MockMvcRequestBuilders.get("/get-books-by-author"))
                .andExpect(status().isNotFound());

        // return 404 if dummy title provided
        mockMvc.perform(MockMvcRequestBuilders.get("/get-books-by-author?author=a"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchBook_noPostsFound() throws Exception {
        given(bookService.getPostsByISBN(anyString())).willReturn(new ArrayList<Post>());

        // return Bad Request (400) if no ISBN provided
        mockMvc.perform(MockMvcRequestBuilders.get("/search-media-coverage-by-isbn"))
                .andExpect(status().isBadRequest());

        // return Not Found (404) if dummy ISBN provided
        mockMvc.perform(MockMvcRequestBuilders.get("/search-media-coverage-by-isbn?isbn=1"))
                .andExpect(status().isNotFound());
    }
}
