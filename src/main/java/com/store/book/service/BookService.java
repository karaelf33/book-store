package com.store.book.service;

import com.store.book.dto.BookDto;
import com.store.book.dto.GenericDTO;
import com.store.book.model.Book;


public interface BookService {

    GenericDTO addBook( BookDto bookDto);
    Book getBookById(Integer bookId);
}
