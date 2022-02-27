package com.store.book.modal;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_ID", nullable = false, unique = true)
    private String userId;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CREDIT_CARD", unique = true)
    private String creditCard;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Order> orders;
}
