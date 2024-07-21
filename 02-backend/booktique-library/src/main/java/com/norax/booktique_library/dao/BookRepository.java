package com.norax.booktique_library.dao;//This declares the package in which the interface resides.

import com.norax.booktique_library.entity.Book;
import org.springframework.data.domain.Page;//Page class which is used to handle paginated data
import org.springframework.data.domain.Pageable;//Interface which provides pagination information
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;//extract query parameters from HTTP requests

import java.util.List;


//JpaRepository is an Interface Spring Data JPA used to provide basic CRUD and pagination or sorting functions
//JpaRepository<Book, Long> indicates that the repository manages Book entities with Long as the ID type.
public interface BookRepository extends JpaRepository<Book, Long> {
    // Method to find books by title containing a specified string, with pagination support.
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);

    @Query("select o from Book o where id in :book_ids")
    List<Book> findBooksByBookIds (@Param("book_ids") List<Long> bookId);
}
