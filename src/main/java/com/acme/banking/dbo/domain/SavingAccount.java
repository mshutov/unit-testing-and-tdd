package com.acme.banking.dbo.domain;

import javax.print.DocFlavor;

import static com.acme.banking.dbo.util.Utils.require;

public class SavingAccount implements Account {
    private final int id;
    private final Client client;
    private final double amount;

    public SavingAccount(int id, Client client, double amount) {
        require(id >= 0);
        require(client != null);
        require(amount >= 0.0);
        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public Account withClient(Client newClient) {
        return new SavingAccount(id, newClient, amount);
    }

    @Override
    public Account withdraw(double amount) {
        require(amount <= this.amount);
        return new SavingAccount(id, client, this.amount - amount);
    }

    @Override
    public Account deposit(double amount) {
        return new SavingAccount(id, client, this.amount + amount);
    }
}
