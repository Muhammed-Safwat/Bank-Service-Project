package com.ecommerce.bankservice.repository;

import com.ecommerce.bankservice.module.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
