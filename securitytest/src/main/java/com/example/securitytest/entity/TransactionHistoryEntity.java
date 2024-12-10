package com.example.securitytest.entity;

import com.example.securitytest.common.AccountConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false, unique = true, length = 100)
    private String transactionId;
//    @Convert(converter = AccountConverter.class)
    @Column(name = "account", nullable = false, length = 100)
    private String account;

    @Column(name = "in_debt", nullable = false)
    private Double inDebt;

    @Column(name = "have", nullable = false)
    private Boolean have;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;
}
