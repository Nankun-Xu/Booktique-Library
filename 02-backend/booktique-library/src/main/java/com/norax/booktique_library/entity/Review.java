package com.norax.booktique_library.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a review for a book in the Booktique Library application.
 */
@Entity // Specifies that this class is an entity and is mapped to a database table
@Table(name = "review") // Specifies the table name in the database
@Data // Lombok annotation to generate boilerplate code like getters, setters, equals, hashCode, and toString methods
public class Review {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the primary key generation strategy to be identity
    @Column(name = "id") // Maps the field to the column in the database table
    private Long id; // Unique identifier for the review

    @Column(name = "user_email") // Maps the field to the 'user_email' column in the database table
    private String userEmail; // Email of the user who wrote the review

    @Column(name = "date") // Maps the field to the 'date' column in the database table
    @CreationTimestamp // Automatically sets the field to the current timestamp when the entity is created
    private Date date; // Date when the review was created

    @Column(name = "rating") // Maps the field to the 'rating' column in the database table
    private double rating; // Rating given by the user, typically on a scale of 1 to 5

    @Column(name = "book_id") // Maps the field to the 'book_id' column in the database table
    private Long bookId; // ID of the book being reviewed

    @Column(name = "review_description") // Maps the field to the 'review_description' column in the database table
    private String reviewDescription; // Description or content of the review

}
