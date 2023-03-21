package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;

import java.util.Collection;

import static com.acme.banking.dbo.util.Utils.require;

public class Processing {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository) {

        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }
    public Client createClient(String name) {
        Client client = new Client(0, name);
        clientRepository.save(client);
        return client;
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return accountRepository.getAccounts(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        require(amount > 0, "Only positive amount can be transferred");
        require(fromAccountId != toAccountId, "Accounts should be different");
        final var from = accountRepository.getAccount(fromAccountId);
        final var to = accountRepository.getAccount(toAccountId);
        require(from != null, "Source account not found");
        require(to != null, "Target account not found");

        var updatedFrom = from.withdraw(amount);
        var updatedTo = to.deposit(amount);

        accountRepository.save(updatedFrom);
        accountRepository.save(updatedTo);
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
