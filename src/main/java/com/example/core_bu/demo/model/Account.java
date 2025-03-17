package com.example.core_bu.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productType;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    private String currency;

    private BigDecimal balance;

    private LocalDateTime creationDate;

    private String branch;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}
