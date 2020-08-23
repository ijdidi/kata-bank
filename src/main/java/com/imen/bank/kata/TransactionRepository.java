package com.imen.bank.kata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class TransactionRepository {
    private final Clock clock;
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void addDeposit(int amount) {
        Transaction depositTransaction = new Transaction(clock.todayAsString(), amount);
        transactions.add(depositTransaction);
    }

    public void addWithdrawal(int amount) {
        Transaction withDrawalTransaction = new Transaction(clock.todayAsString(), -amount);
        transactions.add(withDrawalTransaction);
    }

    public List<Transaction> allTransactions() {
        return unmodifiableList(transactions);
    }
}
