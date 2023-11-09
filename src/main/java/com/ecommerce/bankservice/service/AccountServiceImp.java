package com.ecommerce.bankservice.service;

import com.ecommerce.bankservice.config.AuthenticationProviderService;
import com.ecommerce.bankservice.constants.AccountStatus;
import com.ecommerce.bankservice.constants.AccountType;
import com.ecommerce.bankservice.dto.AccountRequest;
import com.ecommerce.bankservice.dto.AccountResponse;
import com.ecommerce.bankservice.dto.AuthenticationReq;
import com.ecommerce.bankservice.mapper.AccountMapper;
import com.ecommerce.bankservice.module.Account;
import com.ecommerce.bankservice.module.Customer;
import com.ecommerce.bankservice.module.Role;
import com.ecommerce.bankservice.repository.AccountRepository;
import com.ecommerce.bankservice.repository.CustomerRepository;
import com.ecommerce.bankservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;

    private final AuthenticationProviderService authenticationProvider;

    @Override
    @Transactional
    public AccountResponse addAccount(AccountRequest accountRequest) {
        log.info(accountRequest.toString());
        Customer customer = createCustomer(accountRequest);
        Customer savedCustomer = customerRepository.save(customer);
        Account account = createAccount(savedCustomer, accountRequest);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(savedAccount);
    }

    @Override
    public BigDecimal getBalanceByAccountNumber(String accountNumber) {
        return accountRepository.getBalanceByAccountNumber(accountNumber);
    }

    private Account createAccount(Customer savedCustomer, AccountRequest accountRequest) {
        long number = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        Optional<Role> OptionalRole = roleRepository.getRoleByName("USER");
        return Account.builder()
                .accountPassword(passwordEncoder.encode(accountRequest.getPassword()))
                .balance(BigDecimal.ZERO)
                .accountNumber(String.valueOf(number))
                .openedDate(LocalDateTime.now())
                .customer(savedCustomer)
                .accountType(AccountType.SAVINGS)
                .roles(Set.of(OptionalRole.get()))
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    @Override
    public ResponseEntity<?> login(AuthenticationReq authenticationReq) {
        Optional<Account> account = accountRepository.getAccountByAccountNumber(authenticationReq.getAccountNumber());
        log.info("account isPresent", account.isPresent());
        if (account.isPresent()) {
            try {
                log.info("account isPresent", account.isPresent());

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        authenticationReq.getAccountNumber(), authenticationReq.getPassword(), account.get().getAuthorities());
                authentication = authenticationProvider.authenticate(authentication);

                if (authentication.isAuthenticated()) {
                    return ResponseEntity
                            .status(HttpStatus.ACCEPTED)
                            .body("Logged in Successfully");
                }
            } catch (Exception ex) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ex.getMessage());
            }
        }
        return ResponseEntity
                .badRequest()
                .body("This is Account number not found");
    }

    private Customer createCustomer(AccountRequest accountRequest) {
        return Customer.builder()
                .firstName(accountRequest.getFirstName())
                .lastName(accountRequest.getLastName())
                .phone(accountRequest.getPhone())
                .address(accountRequest.getAddress())
                .city(accountRequest.getCity())
                .state(accountRequest.getState())
                .joinDate(LocalDateTime.now())
                .build();
    }
}

