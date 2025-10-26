package com.oussama.acc_servie.web;

import com.oussama.acc_servie.dto.BankAccountRequestDTO;
import com.oussama.acc_servie.dto.BankAccountResponseDTO;
import com.oussama.acc_servie.entities.BankAccount;
import com.oussama.acc_servie.mappers.AccountMapper;
import com.oussama.acc_servie.repositories.BankAccountRepository;
import com.oussama.acc_servie.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService,
            AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    // pour consulter les comptes
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account with id %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);

    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount Update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance() != null)
            account.setBalance(bankAccount.getBalance());
        if (bankAccount.getType() != null)
            account.setType(bankAccount.getType());
        if (bankAccount.getCreatedAt() != null)
            account.setCreatedAt(new Date());
        if (bankAccount.getCurrency() != null)
            account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(bankAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}