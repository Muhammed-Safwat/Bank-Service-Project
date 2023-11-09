package com.ecommerce.bankservice.controller;

import com.ecommerce.bankservice.dto.PaymentRequest;
import com.ecommerce.bankservice.dto.TransactionDto;
import com.ecommerce.bankservice.service.TransactionServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    public final TransactionServiceImp transactionServiceImp;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<TransactionDto>> viewTransactionDetails(@PathVariable String accountNumber) {
        return transactionServiceImp.accountTransactions(accountNumber);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> deposit(@RequestBody PaymentRequest paymentRequest) {
        return transactionServiceImp.deposit(paymentRequest);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDto> withdraw(@RequestBody PaymentRequest paymentRequest) {
        return transactionServiceImp.withdraw(paymentRequest);
    }

}
