package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

import static com.acme.banking.dbo.util.Utils.require;
import static java.util.Collections.unmodifiableCollection;

public class Client {
    private final int id;
    private final String name;
    private final Collection<Account> accounts = new ArrayList<>();

    public Client(int id, String name) {
        require(id >= 0);
        require(name != null && !name.isEmpty());
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public void addAccount(Account account) {
        require(account != null);
        if (account.getClient() == this) {
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        } else {
            account.getClient().removeAccount(account);
            accounts.add(account.withClient(this));
        }
    }

    private void removeAccount(Account account) {
        accounts.remove(account);
    }
}
