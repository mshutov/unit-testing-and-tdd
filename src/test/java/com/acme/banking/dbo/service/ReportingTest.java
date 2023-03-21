package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportingTest {
    @Test
    public void shouldPrintBranchesWithoutAccounts() {
        Reporting sut = new Reporting();

        Branch stubBranch = mock(Branch.class);
        when(stubBranch.getId()).thenReturn(1);
        String result = sut.getReport(stubBranch);

        assertEquals("Branch #1", result);
    }

    @Test
    public void shouldPrintBranchesWithOneAccount() {
        Branch stubBranch = mock(Branch.class);
        Account stubAccount = mock(Account.class);
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(1);
        when(client.getName()).thenReturn("Name1");
        when(stubAccount.getClient()).thenReturn(client);
        when(stubAccount.getId()).thenReturn(1);
        when(stubAccount.getAmount()).thenReturn(100.0);
        when(stubBranch.getAccounts()).thenReturn(List.of(stubAccount));
        when(stubBranch.getId()).thenReturn(1);
        Reporting sut = new Reporting();
        assertEquals("""
                Branch #1
                ==========
                Client #1: Name1
                ----------
                - Account #1: 100.0""", sut.getReport(stubBranch));
    }
}