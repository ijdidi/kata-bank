package feature;

import com.imen.bank.kata.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {
    @Mock
    MyConsole console;
    private Account account;
    @Mock
    Clock clock;


    @Before
    public void initialise() {
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void print_statement_containing_all_transactions() {
        given(clock.todayAsString()).willReturn("10/01/2020", "13/01/2020", "14/01/2020");
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);
        account.printStatement();

        InOrder inOrder = inOrder(console);

        inOrder.verify(console).printLine("Date | Amount | Balance");
        inOrder.verify(console).printLine("14/01/2020 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("13/01/2020 | -100.00 | 900.00");
        inOrder.verify(console).printLine("10/01/2020 | 1000.00 | 1000.00");

    }

}
