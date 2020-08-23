package unitests;

import com.imen.bank.kata.MyConsole;
import com.imen.bank.kata.StatementPrinter;
import com.imen.bank.kata.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {
    StatementPrinter statementPrinter;
    @Mock
    MyConsole console;
    private static final List<Transaction> NO_TRANSACTION = Collections.emptyList();

    @Before
    public void intialise() {
        statementPrinter = new StatementPrinter(console);

    }

    @Test
    public void print_the_header() {

        statementPrinter.print(NO_TRANSACTION);
        verify(console).printLine("Date | Amount | Balance");

    }

    @Test
    public void print_a_transaction() {
        List<Transaction> transactions = transactionsContaining(
                deposit("10/01/2020", 1000),
                withdrawal("13/01/2020", 100),
                deposit("14/01/2020", 500));
        statementPrinter.print(transactions);

        InOrder inOrder = inOrder(console);

        inOrder.verify(console).printLine("Date | Amount | Balance");
        inOrder.verify(console).printLine("14/01/2020 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("13/01/2020 | -100.00 | 900.00");
        inOrder.verify(console).printLine("10/01/2020 | 1000.00 | 1000.00");

    }

    private List<Transaction> transactionsContaining(Transaction... transactions) {
        return asList(transactions);
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}
