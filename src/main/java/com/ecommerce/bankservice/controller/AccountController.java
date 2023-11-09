package com.ecommerce.bankservice.controller;

import com.ecommerce.bankservice.dto.AccountRequest;
import com.ecommerce.bankservice.dto.AccountResponse;
import com.ecommerce.bankservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("api/accounts")
public class AccountController {

    public final AccountService accountService;

    @PostMapping
    public AccountResponse addAccount(@RequestBody @Valid AccountRequest accountRequest) {
        return accountService.addAccount(accountRequest);
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<?> viewAccountBalance(@PathVariable String accountNumber) {
        BigDecimal balance = accountService.getBalanceByAccountNumber(accountNumber);
        return ResponseEntity.ok(balance);
    }

}
