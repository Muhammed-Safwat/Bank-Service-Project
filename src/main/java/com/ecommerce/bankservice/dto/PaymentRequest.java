package com.ecommerce.bankservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PaymentRequest {

    @NotNull
    @NotBlank(message = "Account Number must not be blank")
    private String accountNumber;

    @NotNull(message = "Total Amount must not be null")
    @DecimalMin(value = "10.0", inclusive = false, message = "Total Amount must be greater than 10$")
    private BigDecimal totalAmount;

}
