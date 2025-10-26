package com.oussama.acc_servie.web;

import com.oussama.acc_servie.dto.BankAccountRequestDTO;
import com.oussama.acc_servie.dto.BankAccountResponseDTO;
import com.oussama.acc_servie.entities.BankAccount;
import com.oussama.acc_servie.entities.Customer;
import com.oussama.acc_servie.repositories.BankAccountRepository;
import com.oussama.acc_servie.repositories.CustomerRepository;
import com.oussama.acc_servie.service.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    public BankAccountGraphQLController(BankAccountRepository bankAccountRepository,
                                       CustomerRepository customerRepository,
                                       AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
        this.accountService = accountService;
    }

    @QueryMapping
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    @QueryMapping
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer customerById(@Argument Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @MutationMapping
    public BankAccount addBankAccount(@Argument BankAccountInput input) {
        BankAccountRequestDTO requestDTO = new BankAccountRequestDTO();
        requestDTO.setBalance(input.balance());
        requestDTO.setCurrency(input.currency());
        requestDTO.setType(input.type());
        
        BankAccountResponseDTO responseDTO = accountService.addAccount(requestDTO);
        
        return bankAccountRepository.findById(responseDTO.getId())
                .orElseThrow(() -> new RuntimeException("Failed to create account"));
    }

    @MutationMapping
    public BankAccount updateBankAccount(@Argument String id, @Argument BankAccountInput input) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        
        if (input.balance() != null) {
            account.setBalance(input.balance());
        }
        if (input.currency() != null) {
            account.setCurrency(input.currency());
        }
        if (input.type() != null) {
            account.setType(input.type());
        }
        
        return bankAccountRepository.save(account);
    }

    @MutationMapping
    public Boolean deleteBankAccount(@Argument String id) {
        if (bankAccountRepository.existsById(id)) {
            bankAccountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

record BankAccountInput(Double balance, String currency, com.oussama.acc_servie.enums.AccountType type) {}

