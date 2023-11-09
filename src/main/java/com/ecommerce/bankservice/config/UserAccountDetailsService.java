package com.ecommerce.bankservice.config;

import com.ecommerce.bankservice.module.Account;
import com.ecommerce.bankservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserAccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Optional<Account> optionalUser = accountRepository.getAccountByAccountNumber(accountNumber);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User with username " + accountNumber + " not found !");
        }
        return optionalUser.get();
    }

}
