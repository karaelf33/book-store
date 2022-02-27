package com.stroe.book.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "BOOK")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageURL;


    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;


    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDate dateCreated;


    @JsonIgnore
    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<Cart> carts;


}
