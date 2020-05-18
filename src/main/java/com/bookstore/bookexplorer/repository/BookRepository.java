package com.bookstore.bookexplorer.repository;

import com.bookstore.bookexplorer.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByISBN(String ISBN);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + 1 WHERE b.ISBN = :isbn")
    void addCopyByISBN(@Param("isbn") String ISBN);

    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies - 1 WHERE b.ISBN = :isbn")
    void removeCopyByISBN(@Param("isbn") String ISBN);

    //check null searches
}
