package com.store.book.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "session_id")
    private String sessionId;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;


    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

}
