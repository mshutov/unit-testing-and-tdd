package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class MutualConnectionBetweenAccountAndClientTest {
    @Test
    public void shouldAddAccountToAccountsWhenAddedToClient() {
        Client client = new Client(0, "A Client");
        Account account = new SavingAccount(0, client, 0.0);

        assumeThat(client.getAccounts().isEmpty());

        client.addAccount(account);

        assertAll(
                () -> assertEquals(1, client.getAccounts().size()),
                () -> assertTrue(client.getAccounts().contains(account)),
                () -> assertEquals(client, account.getClient())
        );
    }

    @Test
    public void shouldUpdateClientWhenAccountAddedToClientWithWrongClient() {
        int accountId = 0;
        Client client = new Client(0, "A Client");
        Client wrongClient = new Client(1, "Wrong Client");
        Account account = new SavingAccount(accountId, wrongClient, 0.0);

        assumeThat(client.getAccounts().isEmpty());
        assumeThat(wrongClient == account.getClient());

        client.addAccount(account);

        assertAll(
                () -> assertEquals(1, client.getAccounts().size()),
                () -> assertTrue(client.getAccounts().stream()
                        .allMatch(acc -> acc.getClient() == client && acc.getId() == accountId))
        );
    }
}
