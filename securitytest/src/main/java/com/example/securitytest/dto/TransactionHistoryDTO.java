package com.example.securitytest.dto;

import com.example.securitytest.common.AccountConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryDTO {

    private Long id;
    private String transactionId;
    private String account;
    private String destinationAccount;
    private Double inDebt;
    private Boolean have;
    private LocalDateTime time;
}
