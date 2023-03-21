package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;

import java.util.Collection;

public interface AccountRepository {
    Collection<Account> getAccounts(int clientId);

    Account getAccount(int accountId);

    Account save(Account from);
}
