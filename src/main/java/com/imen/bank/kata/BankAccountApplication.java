package com.imen.bank.kata;

public class BankAccountApplication {
    public static void main(String[] args) {
        Clock clock = new Clock();
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        MyConsole console = new MyConsole();
        StatementPrinter statementsPrinter = new StatementPrinter(console);
        Account account = new Account(transactionRepository, statementsPrinter);

        account.deposit(1000);
        account.withdraw(400);
        account.deposit(100);

        account.printStatement();

    }
}
