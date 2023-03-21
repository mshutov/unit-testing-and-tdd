package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return "Branch #" + rootBranch.getId() + "\n==========\n" +
                rootBranch.getAccounts().stream().collect(groupingBy(Account::getClient)).entrySet().stream()
                        .sorted(Comparator.comparing(entry -> entry.getKey().getId()))
                        .map(Reporting::printClientAndAccounts)
                        .collect(Collectors.joining("\n\n"));
    }

    private static String printClientAndAccounts(Map.Entry<Client, List<Account>> entry) {
        Client client = entry.getKey();
        List<Account> accounts = entry.getValue();
        return printClient(client)
                + "\n----------\n"
                + accounts.stream()
                .map(Reporting::printAccount)
                .collect(Collectors.joining("\n"));
    }

    private static String printAccount(Account acc) {
        return "- Account #" + acc.getId() + ": " + acc.getAmount();
    }

    private static String printClient(Client client) {
        return "Client #" + client.getId() + ": " + client.getName();
    }
}
