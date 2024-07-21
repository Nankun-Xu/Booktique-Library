package com.norax.booktique_library.service;

import com.norax.booktique_library.dao.BookRepository;
import com.norax.booktique_library.dao.CheckoutRepository;
import com.norax.booktique_library.entity.Book;
import com.norax.booktique_library.entity.Checkout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service class for handling book-related operations.
 */
@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;
    private CheckoutRepository checkoutRepository;

    /**
     * Constructor for BookService.
     *
     * @param bookRepository     the repository for book data operations
     * @param checkoutRepository the repository for checkout data operations
     */
    public BookService(BookRepository bookRepository, CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
    }

    /**
     * Checks out a book for a user.
     *
     * @param userEmail the email of the user
     * @param bookId    the ID of the book to be checked out
     * @return the checked-out book
     * @throws Exception if the book doesn't exist, is already checked out by the user, or no copies are available
     */
    public Book checkoutBook(String userEmail, Long bookId) throws Exception {

        // Find the book by its ID
        Optional<Book> book = bookRepository.findById(bookId);

        // Check if the user has already checked out this book
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        // Throw an exception if the book doesn't exist, is already checked out by the user, or no copies are available
        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't exist or already checked out by user");
        }

        // Decrease the number of available copies by 1
        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        // Create a new checkout record
        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );

        // Save the checkout record
        checkoutRepository.save(checkout);

        // Return the checked-out book
        return book.get();
    }

    /**
     * Checks if a user has already checked out a specific book.
     *
     * @param userEmail the email of the user
     * @param bookId    the ID of the book
     * @return true if the user has already checked out the book, false otherwise
     */
    public Boolean checkoutBookByUser(String userEmail, Long bookId) {
        // Check if the user has already checked out this book
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateCheckout != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Counts the number of books currently checked out by a user.
     *
     * @param userEmail the email of the user
     * @return the number of books currently checked out by the user
     */
    public int currentLoansCount(String userEmail) {
        // Return the count of books checked out by the user
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }
}
