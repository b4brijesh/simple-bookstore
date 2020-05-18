package com.bookstore.bookexplorer.exception;

public class NoPostsFoundException extends RuntimeException {
    public NoPostsFoundException() {
        super("No posts found");
    }
}
