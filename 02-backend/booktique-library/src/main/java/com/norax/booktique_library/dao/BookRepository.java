package com.norax.booktique_library.dao;

import com.norax.booktique_library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


//JpaRepository is an Interface Spring Data JPA used to provide basic CRUD and pagination or sorting functions
public interface BookRepository extends JpaRepository<Book, Long> { //long is 'id' for key, book is object


}
