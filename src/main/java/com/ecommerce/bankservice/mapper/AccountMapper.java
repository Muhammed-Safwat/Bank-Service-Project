package com.ecommerce.bankservice.mapper;


import com.ecommerce.bankservice.dto.AccountResponse;
import com.ecommerce.bankservice.module.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    AccountResponse toAccountResponse(Account account);

}
