package com.store.book.service.impl;

import com.store.book.constant.Constant;
import com.store.book.dto.BookDto;
import com.store.book.dto.GenericDTO;
import com.store.book.exception.BookNotExistException;
import com.store.book.model.Book;
import com.store.book.repository.BookRepository;
import com.store.book.service.BookService;
import com.store.book.utils.OperationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Override
    public GenericDTO addBook(BookDto bookDto) {


        if (isPresentBookWithSameCode(bookDto)) {
            return OperationUtils.returnMessageHandling(
                    bookDto,
                    Constant.FAIL_CODE,
                    false,
                    Constant.BOOK_EXIST
            );
        }
        Book book = Book.builder()
                .bookName(bookDto.getBookName())
                .author(bookDto.getAuthor())
                .code(bookDto.getCode())
                .price(bookDto.getPrice())
                .imageURL(bookDto.getImageUrl())
                .stock(bookDto.getStock())
                .dateCreated(LocalDate.now())
                .build();
        // create  stock system and insert new book's stock

        try {
            bookRepository.save(book);
            return OperationUtils.returnMessageHandling(
                    bookDto,
                    Constant.SUCCESS_CODE,
                    true,
                    Constant.SUCCESS_MESSAGE
            );
        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    null,
                    Constant.FAIL_CODE,
                    false,
                    Constant.SUCCESS_MESSAGE
            );
        }
    }

    private boolean isPresentBookWithSameCode(BookDto bookDto) {
        return bookRepository.existsByCode(bookDto.getCode());
    }

    public Book getBookById(Integer bookId) throws BookNotExistException {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty())
            throw new BookNotExistException("Book id is invalid " + bookId);
        return bookOptional.get();
    }

    @Override
    @Transactional
    public GenericDTO updateBookStock(String bookCode, Integer stock) {

        try {
            Book book = bookRepository.findByCode(bookCode);
            book.setStock(stock);
            bookRepository.save(book);
            logger.error("BOOK SUCCESSFULLY UPDATED");

            return OperationUtils.returnMessageHandling(
                    BookDto.convertEntityToDto(book),
                    Constant.SUCCESS_CODE,
                    true,
                    Constant.SUCCESS_MESSAGE
            );
        } catch (Exception e) {
            logger.error("BOOK COULD NOT UPDATED{}", e.getMessage(), e);
            return OperationUtils.returnMessageHandling(
                    null,
                    Constant.FAIL_CODE,
                    false,
                    e.getMessage()
            );
        }
    }
}
