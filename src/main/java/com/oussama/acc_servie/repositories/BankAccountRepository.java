package com.oussama.acc_servie.repositories;

import com.oussama.acc_servie.entities.BankAccount;
import com.oussama.acc_servie.enums.AccountType;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Hidden
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByType(@Param("type") AccountType type);
}