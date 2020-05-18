package com.bookstore.bookexplorer.service;

import com.bookstore.bookexplorer.exception.BookNotFoundException;
import com.bookstore.bookexplorer.model.Book;
import com.bookstore.bookexplorer.model.Post;
import com.bookstore.bookexplorer.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // GET services
    public Optional<Book> getBookByISBN(String ISBN) {
        return bookRepository.findByISBN(ISBN);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Post> getPostsByISBN(String ISBN) {
        Book book = getBookByISBN(ISBN).orElseThrow(() -> new BookNotFoundException("ISBN", ISBN));

        String bookTitle = book.getTitle();
        List<Post> allPosts = GetPostsService.getPosts();
        List<Post> matchedPosts = new ArrayList<Post>();
        for (Post post: allPosts) {
            if(post.getPostTitle().toLowerCase().contains(bookTitle.toLowerCase()) ||
                    post.getPostBody().toLowerCase().contains(bookTitle.toLowerCase()))
                matchedPosts.add(post);
        }
        return matchedPosts;
    }

    // POST service
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Book addBook(Book newBook) {
        String bookISBN = newBook.getISBN();
        Optional<Book> bookDao = getBookByISBN(bookISBN);
        if (!bookDao.isPresent()) {
            bookRepository.save(newBook);
            bookRepository.addCopyByISBN(newBook.getISBN());
            return newBook;
        } else {
            bookRepository.addCopyByISBN(bookDao.get().getISBN());
            return bookDao.get();
        }
        // check for non-matching book details and throw errors
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Book buyBook(Book newBook) {
        String ISBN = newBook.getISBN();
        Book bookDao = getBookByISBN(ISBN).orElseThrow(() -> new BookNotFoundException("ISBN", ISBN));

        // bookDao.setCopies(bookDao.getCopies()-1);
        bookRepository.removeCopyByISBN(bookDao.getISBN());
        if (bookDao.getCopies() == 1) {
            // System.out.println("Added a copy too!");
            bookRepository.addCopyByISBN(bookDao.getISBN());
        }
        return bookDao;

        // TODO: check for concurrency issues
    }

}
