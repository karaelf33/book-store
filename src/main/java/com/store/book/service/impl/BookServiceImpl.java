package com.store.book.service.impl;

import com.store.book.constant.Constant;
import com.store.book.dto.BookDto;
import com.store.book.dto.GenericDTO;
import com.store.book.exception.BookNotExistException;
import com.store.book.modal.Book;
import com.store.book.repository.BookRepository;
import com.store.book.service.BookService;
import com.store.book.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public GenericDTO addBook(BookDto bookDto) {

        Book book = Book.builder()
                .bookName(bookDto.getBookName())
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .imageURL(bookDto.getImageUrl())
                .build();
        // learn stok system
                //.availableItemCount(bookDto.getAvailableItemCount())

        bookRepository.save(book);
        return OperationUtils.returnMessageHandling(
                bookDto,
                Constant.SUCCESS_CODE,
                true,
                Constant.SUCCESS_MESSAGE
        );
    }

    public Book getBookById(Integer bookId) throws BookNotExistException {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty())
            throw new BookNotExistException("Book id is invalid " + bookId);
        return bookOptional.get();
    }
}
