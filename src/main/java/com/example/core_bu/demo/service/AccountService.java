package com.example.core_bu.demo.service;

import com.example.core_bu.demo.model.Account;
import com.example.core_bu.demo.model.Client;
import com.example.core_bu.demo.repository.AccountRepository;
import com.example.core_bu.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public Account createAccount(Long clientId, Account account) throws ChangeSetPersister.NotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        account.setClient(client);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) throws ChangeSetPersister.NotFoundException {
        return accountRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Long id, Account account) {
        Account accountToUpdate = accountRepository.findById(id).orElseThrow();
        accountToUpdate.setBalance(account.getBalance());
        accountToUpdate.setCurrency(account.getCurrency());
        accountToUpdate.setProductType(account.getProductType());
        return accountRepository.save(accountToUpdate);
    }
}
