package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportingTest {
    @Mock
    Client stubClient;
    @Mock
    Client stubClient2;
    @Mock
    Account stubAccount;
    @Mock
    Account stubAccount2;
    @Mock
    Account stubAccount3;
    @Mock
    Branch stubBranch;

    private List<Account> accounts = List.of();

    @BeforeEach
    public void setup() {
        when(stubBranch.getId()).thenReturn(1);
        when(stubClient.getId()).thenReturn(1);
        when(stubClient.getName()).thenReturn("Name1");
        when(stubClient2.getId()).thenReturn(2);
        when(stubClient2.getName()).thenReturn("Name2");

        accounts = new ArrayList<>();



        when(stubAccount.getClient()).thenReturn(stubClient);
        when(stubAccount.getId()).thenReturn(1);
        when(stubAccount.getAmount()).thenReturn(100.0);
        when(stubAccount2.getClient()).thenReturn(stubClient);
        when(stubAccount2.getId()).thenReturn(2);
        when(stubAccount2.getAmount()).thenReturn(200.0);

        when(stubAccount3.getClient()).thenReturn(stubClient2);
        when(stubAccount3.getId()).thenReturn(3);
        when(stubAccount3.getAmount()).thenReturn(300.0);
    }

    @Test
    public void shouldPrintBranchesWithoutAccounts() {
        Reporting sut = new Reporting();


        String result = sut.getReport(stubBranch);

        assertEquals("Branch #1", result);
    }

    @Test
    public void shouldPrintBranchesWithOneAccount() {

        when(stubBranch.getAccounts()).thenReturn(List.of(stubAccount));
        Reporting sut = new Reporting();
        assertEquals("""
                Branch #1
                ==========
                Client #1: Name1
                ----------
                - Account #1: 100.0""", sut.getReport(stubBranch));
    }

    @Test
    public void shouldPrintBranchesWithTwoAccounts() {
        when(stubBranch.getAccounts()).thenReturn(List.of(stubAccount, stubAccount2));
        Reporting sut = new Reporting();
        assertEquals("""
                Branch #1
                ==========
                Client #1: Name1
                ----------
                - Account #1: 100.0
                - Account #2: 200.0""", sut.getReport(stubBranch));
    }

    @Test
    public void shouldPrintBranchesWithAccountsForTwoClients() {
        when(stubBranch.getAccounts()).thenReturn(List.of(stubAccount, stubAccount2, stubAccount3));
        Reporting sut = new Reporting();
        assertEquals("""
                Branch #1
                ==========
                Client #1: Name1
                ----------
                - Account #1: 100.0
                - Account #2: 200.0
                
                Client #2: Name2
                ----------
                - Account #3: 300.0""", sut.getReport(stubBranch));
    }
}