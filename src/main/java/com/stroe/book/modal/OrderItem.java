package com.stroe.book.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ORDER_ITEMS")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "price")
    private @NotNull BigDecimal price;


    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "id")
    private Order orders;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public OrderItem(Order orders, @NotNull Book book, @NotNull int quantity, @NotNull BigDecimal price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.orders= orders;
        this.createdDate = new Date();
    }
}
