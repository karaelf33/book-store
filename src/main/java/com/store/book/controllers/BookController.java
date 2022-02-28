package com.store.book.controllers;

import com.store.book.dto.BookDto;
import com.store.book.dto.GenericDTO;
import com.store.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("")
    public GenericDTO createBook(@RequestBody BookDto bookDto) {

        return bookService.addBook(bookDto);
    }


    @PostMapping("/{bookId}")
    public GenericDTO updateBookStock(@PathVariable Integer bookId, @RequestParam Integer stock) {

        return bookService.updateBookStock(bookId, stock);
    }
}
