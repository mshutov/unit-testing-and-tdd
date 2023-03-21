package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcessingTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    ClientRepository clientRepository;
    @InjectMocks
    Processing sut;
    @Mock
    Client client;
    @Mock
    Account account;

    @Test
    public void shouldSaveClientWhenClientCreated() {
        sut.createClient("A Client");

        verify(clientRepository).save(any(Client.class));
    }

    @Test
    public void shouldReturnListOfAccounts() {
        int clientId = 0;
        List<Account> accountList = List.of(account);
        when(accountRepository.getAccounts(clientId)).thenReturn(accountList);

        Collection<Account> accounts = sut.getAccountsByClientId(clientId);

        assertEquals(accountList, accounts);
    }

    @Test
    public void shouldReturnEmptyListWhenNoAccountsForClient() {
        int clientId = 0;
        when(accountRepository.getAccounts(clientId)).thenReturn(emptyList());

        Collection<Account> accounts = sut.getAccountsByClientId(clientId);

        assertEquals(emptyList(), accounts);
    }

    @Test
    public void shouldNotAllowTransferIfAmountIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> sut.transfer(0, 1, -1.0));
    }

    @Test
    public void shouldNotAllowTransferIfAccountIdsAreSame() {
        assertThrows(IllegalArgumentException.class, () -> sut.transfer(0, 0, 1.0));
    }

    @Test
    public void shouldNotAllowTransferIfFromAccountIsNotFound() {
        assertThrows(IllegalArgumentException.class, () -> sut.transfer(0, 1, 1.0));
    }

    @Test
    public void shouldNotAllowTransferIfToAccountIsNotFound() {
        int fromAccountId = 0;
        when(accountRepository.getAccount(fromAccountId)).thenReturn(account);

        assertThrows(IllegalArgumentException.class, () -> sut.transfer(fromAccountId, 1, 1.0));
    }


}
