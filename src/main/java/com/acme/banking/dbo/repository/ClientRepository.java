package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {
    Client findById(int clientId);
    void save(Client client);
}
