package com.ecommerce.bankservice.dto;

import com.ecommerce.bankservice.constants.TransactionStatus;
import com.ecommerce.bankservice.constants.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionDto {

    private BigDecimal amount;

    private String transactionDescription;

    private LocalDateTime transactionDate;

    private TransactionType type;

    private TransactionStatus status;
}
