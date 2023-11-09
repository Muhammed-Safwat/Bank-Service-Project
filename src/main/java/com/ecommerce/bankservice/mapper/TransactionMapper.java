package com.ecommerce.bankservice.mapper;

import com.ecommerce.bankservice.dto.TransactionDto;
import com.ecommerce.bankservice.module.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    List<TransactionDto> transactionDtoList(List<Transaction> transactionList);

    TransactionDto toDto(Transaction transaction);
}
