package com.bookstore.bookexplorer.controller;

import com.bookstore.bookexplorer.exception.BookNotFoundException;
import com.bookstore.bookexplorer.model.Book;
import com.bookstore.bookexplorer.model.Post;
import com.bookstore.bookexplorer.service.BookService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookStoreController {

    BookService bookService;

    public BookStoreController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search-book-by-isbn")
    public Book getBookByISBN(@RequestParam(value = "isbn") String ISBN) {
        return bookService.getBookByISBN(ISBN).orElseThrow(() -> new BookNotFoundException("ISBN", ISBN));
    }

    @GetMapping("/search-books-by-title")
    public List<Book> getBooksByTitle(@RequestParam(value = "title") String title) {
        List<Book> books = bookService.getBooksByTitle(title);
        if (books.isEmpty()) throw new BookNotFoundException("title", title);
        else return books;
    }

    @GetMapping("/search-books-by-author")
    public List<Book> getBooksByAuthor(@RequestParam(value = "author") String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        if (books.isEmpty()) throw new BookNotFoundException("author", author);
        else return books;
    }

    @GetMapping("/search-media-coverage-by-isbn")
    public List<Post> getPostsByISBN(@RequestParam(value = "isbn") String ISBN) {
        return bookService.getPostsByISBN(ISBN);

        // TODO: add no posts found exception and controller advice
    }

    @PostMapping("/add-book")
    public Book addBook(@Valid @RequestBody Book newBook) {
        return bookService.addBook(newBook);
    }

    @PostMapping("/buy-book")
    public Book buyBookByISBN(@Valid @RequestBody Book newBook) {
        return bookService.buyBook(newBook);
    }

}
