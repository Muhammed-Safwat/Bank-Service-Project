package com.ecommerce.bankservice.service;

import com.ecommerce.bankservice.dto.PaymentRequest;
import com.ecommerce.bankservice.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {

    ResponseEntity<List<TransactionDto>> accountTransactions(String accountNumber);

    ResponseEntity<TransactionDto> deposit(PaymentRequest paymentRequest);

    ResponseEntity<TransactionDto> withdraw(PaymentRequest paymentRequest);

}
