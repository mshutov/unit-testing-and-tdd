package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        Reporting sut = new Reporting();
        assertEquals("""
                Branch #1
                    ==========
                    Client #1: Name1
                    ----------
                    - Account #1: 100.0
                """, sut.getReport(stubBranch));
    }
}