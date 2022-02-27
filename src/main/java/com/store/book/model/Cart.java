package com.store.book.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "id")
    private Book book;

    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;


    private int quantity;

    public Cart() {
    }


    public Cart(Book book, int quantity, Customer user){
        this.customer = user;
        this.book = book;
        this.quantity = quantity;
        this.createdDate = new Date();
    }



}
