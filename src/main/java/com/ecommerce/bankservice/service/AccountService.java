package com.ecommerce.bankservice.service;

import com.ecommerce.bankservice.dto.AccountRequest;
import com.ecommerce.bankservice.dto.AccountResponse;
import com.ecommerce.bankservice.dto.AuthenticationReq;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface AccountService {

    AccountResponse addAccount(AccountRequest accountRequest);

    BigDecimal getBalanceByAccountNumber(String accountNumber);

    ResponseEntity login(AuthenticationReq authenticationReq);

}
