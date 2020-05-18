package com.bookstore.bookexplorer.controller;

import com.bookstore.bookexplorer.exception.BookNotFoundException;
import com.bookstore.bookexplorer.exception.NoPostsFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoPostsFoundAdvice {

    @ResponseBody
    @ExceptionHandler(NoPostsFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noPostsFoundHandler(NoPostsFoundException exception) {
        return exception.getMessage();
    }
}
