package com.norax.booktique_library.controller;

import com.norax.booktique_library.entity.Book;
import com.norax.booktique_library.service.BookService;
import com.norax.booktique_library.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping("/secure/currentloans")
//    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value = "Authorization") String token)
//            throws Exception
//    {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        return bookService.currentLoans(userEmail);
//    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(value = "Authorization") String token) {
//        String userEmail = "testuser@email.com";
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token,
                                      @RequestParam Long bookId) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        String userEmail = "testuser@email.com";
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook (@RequestHeader(value = "Authorization") String token,
                              @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        String userEmail = "testuser@email.com";
        return bookService.checkoutBook(userEmail, bookId);
    }

//    @PutMapping("/secure/return")
//    public void returnBook(@RequestHeader(value = "Authorization") String token,
//                           @RequestParam Long bookId) throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        bookService.returnBook(userEmail, bookId);
//    }
//
//    @PutMapping("/secure/renew/loan")
//    public void renewLoan(@RequestHeader(value = "Authorization") String token,
//                          @RequestParam Long bookId) throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        bookService.renewLoan(userEmail, bookId);
//    }
}
