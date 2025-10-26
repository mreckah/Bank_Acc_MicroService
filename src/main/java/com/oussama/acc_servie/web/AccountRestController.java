package com.oussama.acc_servie.web;

import com.oussama.acc_servie.dto.BankAccountRequestDTO;
import com.oussama.acc_servie.dto.BankAccountResponseDTO;
import com.oussama.acc_servie.entities.BankAccount;
import com.oussama.acc_servie.mappers.AccountMapper;
import com.oussama.acc_servie.repositories.BankAccountRepository;
import com.oussama.acc_servie.service.AccountService;
import org.springframework.web.bind.annotation.*;

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
    public List<BankAccountResponseDTO> bankAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(accountMapper::fromBankAccount)
                .toList();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO bankAccount(@PathVariable String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account with id %s not found", id)));
        return accountMapper.fromBankAccount(bankAccount);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);

    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO Update(@PathVariable String id, @RequestBody BankAccountRequestDTO requestDTO) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account with id %s not found", id)));
        if (requestDTO.getBalance() != null)
            account.setBalance(requestDTO.getBalance());
        if (requestDTO.getType() != null)
            account.setType(requestDTO.getType());
        if (requestDTO.getCurrency() != null)
            account.setCurrency(requestDTO.getCurrency());
        BankAccount updatedAccount = bankAccountRepository.save(account);
        return accountMapper.fromBankAccount(updatedAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}