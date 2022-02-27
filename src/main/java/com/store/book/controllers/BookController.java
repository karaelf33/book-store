package com.store.book.controllers;

import com.store.book.dto.BookDto;
import com.store.book.dto.GenericDTO;
import com.store.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public GenericDTO createProduct(@RequestBody BookDto bookDto){

        return bookService.addBook(bookDto);
    }

    @RequestMapping("/welcome")
    public String welcomepage() {
        return "Welcome to Yawin Tutor";
    }

    // update book stock
}
