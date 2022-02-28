package com.store.book.dto;

import com.store.book.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @NotNull(message = "bookName should not be null!")
    @NotEmpty(message = "bookName should not be empty!")
    private String bookName;
    private String author;

    @Min(value = 0)
    private BigDecimal price;
    private String imageUrl;
    private String code;
    private Integer stock;
    private int availableItemCount;

    public static BookDto convertEntityToDto(Book book) {
        BookDto bookDto=new BookDto();
        bookDto.setBookName(book.getBookName());
        bookDto.setPrice(book.getPrice());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setImageUrl(book.getImageURL());
        bookDto.setCode(book.getCode());
        bookDto.setStock(book.getStock());
        return bookDto;
    }
}
