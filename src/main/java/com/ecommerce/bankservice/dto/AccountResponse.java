package com.ecommerce.bankservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    @NotNull(message = "Account Type must not be null")
    private String accountType;

    @NotNull(message = "Account Number must not be null")
    private String accountNumber;

    @NotNull(message = "Balance must not be null")
    private BigDecimal balance;
}
