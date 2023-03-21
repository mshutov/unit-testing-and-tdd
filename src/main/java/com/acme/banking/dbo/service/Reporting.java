package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return "Branch #" + rootBranch.getId() + "\n==========\n" +
                rootBranch.getAccounts().stream().collect(groupingBy(Account::getClient)).entrySet().stream()
                        .map(entry -> "Client #" + entry.getKey().getId() + ": " + entry.getKey().getName() + "\n----------\n" + entry.getValue().stream().map(acc -> "- Account #" + acc.getId() + ": " + acc.getAmount()).collect(Collectors.joining("\n"))).collect(Collectors.joining("\n"));
    }
}
