package com.ecommerce.bankservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class AccountRequest {

    @NotNull(message = "Account Type must be provided")
    private String accountType;

    @NotNull(message = "First Name must be provided")
    private String firstName;

    @NotNull(message = "Last Name must be provided")
    private String lastName;

    @NotNull(message = "Password must be provided")
    private String password;

    private String address;

    private String city;

    private String state;

    @NotNull(message = "phone number must be provided")
    private String phone;

}
