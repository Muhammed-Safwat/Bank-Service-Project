package com.ecommerce.bankservice.service;

import com.ecommerce.bankservice.constants.TransactionStatus;
import com.ecommerce.bankservice.constants.TransactionType;
import com.ecommerce.bankservice.dto.PaymentRequest;
import com.ecommerce.bankservice.dto.TransactionDto;
import com.ecommerce.bankservice.mapper.TransactionMapper;
import com.ecommerce.bankservice.module.Account;
import com.ecommerce.bankservice.module.Transaction;
import com.ecommerce.bankservice.repository.AccountRepository;
import com.ecommerce.bankservice.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImp implements TransactionService {

    public final AccountRepository accountRepository;

    public final TransactionRepository transactionRepository;

    public final TransactionMapper transactionMapper;

    public ResponseEntity<List<TransactionDto>> accountTransactions(String accountNumber) {
        List<Transaction> transactionList = accountRepository.getTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactionMapper.transactionDtoList(transactionList));
    }

    @Transactional
    public ResponseEntity<TransactionDto> deposit(PaymentRequest paymentRequest) {
        Optional<Account> optionalAccount = accountRepository.getAccountByAccountNumber(paymentRequest.getAccountNumber());
        if (optionalAccount.isEmpty()) {
            Transaction faildTransaction = createTransaction(null, TransactionType.DEPOSIT, TransactionStatus.CANCELLED, paymentRequest.getTotalAmount());
            faildTransaction.setTransactionDescription("Account id not valid");
            transactionRepository.save(faildTransaction);
            return ResponseEntity.badRequest()
                    .body(transactionMapper.toDto(faildTransaction));
        }
        Account account = optionalAccount.get();
        account.setBalance(account.getBalance().add(paymentRequest.getTotalAmount()));
        Account savedAccount = accountRepository.save(account);
        Transaction transaction = createTransaction(savedAccount, TransactionType.DEPOSIT, TransactionStatus.COMPLETED, paymentRequest.getTotalAmount());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(transactionMapper.toDto(savedTransaction));
    }

    public ResponseEntity<TransactionDto> withdraw(PaymentRequest paymentRequest) {
        Optional<Account> optionalAccount = accountRepository.getAccountByAccountNumber(paymentRequest.getAccountNumber());
        if (optionalAccount.isEmpty()) {
            Transaction faildTransaction = createTransaction(null, TransactionType.WITHDRAW, TransactionStatus.CANCELLED, paymentRequest.getTotalAmount());
            faildTransaction.setTransactionDescription("Account id not valid");
            transactionRepository.save(faildTransaction);
            return ResponseEntity.badRequest()
                    .body(transactionMapper.toDto(faildTransaction));
        }
        Account account = optionalAccount.get();
        log.info("Account Balance {}", account.getBalance().doubleValue());
        log.info("Get Total amount {}", paymentRequest.getTotalAmount().doubleValue());
        if (account.getBalance().doubleValue() < paymentRequest.getTotalAmount().doubleValue()) {
            Transaction faildTransaction = createTransaction(null, TransactionType.WITHDRAW, TransactionStatus.CANCELLED, paymentRequest.getTotalAmount());
            faildTransaction.setTransactionDescription("Account Balance Not enough");
            transactionRepository.save(faildTransaction);
            return ResponseEntity.badRequest()
                    .body(transactionMapper.toDto(faildTransaction));
        }
        account.setBalance(account.getBalance().subtract(paymentRequest.getTotalAmount()));
        Account savedAccount = accountRepository.save(account);
        Transaction transaction = createTransaction(savedAccount, TransactionType.WITHDRAW, TransactionStatus.COMPLETED, paymentRequest.getTotalAmount());
        transaction.setTransactionDescription("Withdraw of $" + paymentRequest.getTotalAmount() + " into account " + account.getAccountNumber());
        Transaction savedTransaction = transactionRepository.save(transaction);

        return ResponseEntity.ok(transactionMapper.toDto(savedTransaction));
    }

    private Transaction createTransaction(Account account, TransactionType type, TransactionStatus status, BigDecimal amount) {
        return Transaction.builder()
                .account(account)
                .transactionDate(LocalDateTime.now())
                .type(type)
                .status(status)
                .amount(amount)
                .build();
    }
}
