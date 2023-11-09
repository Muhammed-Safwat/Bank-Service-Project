package com.ecommerce.bankservice.controller;

import com.ecommerce.bankservice.dto.AuthenticationReq;
import com.ecommerce.bankservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationReq authenticationReq) {
        return accountService.login(authenticationReq);
    }

}
