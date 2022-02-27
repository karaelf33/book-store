package com.store.book.exception;

public class BookNotExistException extends IllegalArgumentException {
    public BookNotExistException(String msg) {
        super(msg);

}
}
