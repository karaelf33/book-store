package com.store.book.dto;

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

    private int availableItemCount;
}
