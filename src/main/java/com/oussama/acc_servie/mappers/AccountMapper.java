package com.oussama.acc_servie.mappers;

import com.oussama.acc_servie.dto.BankAccountRequestDTO;
import com.oussama.acc_servie.dto.BankAccountResponseDTO;
import com.oussama.acc_servie.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        if (bankAccount == null) {
            return null;
        }
        return BankAccountResponseDTO.builder()
                .id(bankAccount.getId())
                .createdAt(bankAccount.getCreatedAt())
                .balance(bankAccount.getBalance())
                .currency(bankAccount.getCurrency())
                .type(bankAccount.getType())
                .build();
    }
}

