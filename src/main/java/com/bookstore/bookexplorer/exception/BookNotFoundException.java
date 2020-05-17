package com.bookstore.bookexplorer.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String param, String value) {
        super("Could not find book with " + param + ": " + value);
    }
}
