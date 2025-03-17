package com.example.core_bu.demo.controller;

import com.example.core_bu.demo.model.Account;
import com.example.core_bu.demo.service.AccountService;
import com.example.core_bu.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final ClientService clientService;

    @PostMapping("/{clientId}")
    public ResponseEntity<Account> createAccount(@PathVariable Long clientId, @RequestBody Account account) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(clientId, account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(id));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Account>> getAccounstByClientID(@PathVariable Long clientId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClientById(clientId).getAccounts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(id, account));
    }
}
