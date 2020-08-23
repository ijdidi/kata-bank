package com.imen.bank.kata;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {
    private static final String STATEMENT_HEADER = "Date | Amount | Balance";
    private MyConsole console;
    private DecimalFormat decimalFormater = new DecimalFormat("#.00");

    public StatementPrinter(MyConsole console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(STATEMENT_HEADER);
        printStatementLines(transactions);

    }

    private String statementLine(Transaction transaction, AtomicInteger runningBalance) {
        return transaction.date()
                + " | "
                + decimalFormater.format(transaction.amount())
                + " | "
                + decimalFormater.format(runningBalance.addAndGet(transaction.amount()));
    }

    private void printStatementLines(List<Transaction> transactions) {
        AtomicInteger runningBalance = new AtomicInteger(0);
        transactions.stream().map(transaction -> statementLine(transaction, runningBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
        System.out.println(runningBalance);

    }
}
