package com.ecommerce.bankservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationReq {

    @NotBlank(message = "accountNumber cannot be blank")
    private String accountNumber;

    @NotBlank(message = "Password cannot be blank")
    private String password;

}
