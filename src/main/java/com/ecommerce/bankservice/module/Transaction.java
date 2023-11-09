package com.ecommerce.bankservice.module;

import com.ecommerce.bankservice.constants.TransactionStatus;
import com.ecommerce.bankservice.constants.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(generator = "TransactionIdGenerator")
    @GenericGenerator(name = "TransactionIdGenerator", strategy = "com.ecommerce.bankservice.util.TransactionIdGenerator")
    private String id;

    private BigDecimal amount;

    private String transactionDescription;

    @CreatedDate
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
