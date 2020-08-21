package feature;

import com.imen.bank.kata.Account;
import com.imen.bank.kata.MyConsole;
import com.imen.bank.kata.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {
    @Mock MyConsole console;
    private Account account;


    @Before
    public void initialise(){
         TransactionRepository transactionRepository = new TransactionRepository();
        account = new Account(transactionRepository);
    }

    @Test
    public void print_statement_containing_all_transactions(){
        
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);
        account.printStatement();

        InOrder inOrder = inOrder(console);

        inOrder.verify(console).printLine("Date || Amount || Balance");
        inOrder.verify(console).printLine("14/01/2012 || -500   || 2500");
        inOrder.verify(console).printLine("13/01/2012 || 2000   || 3000");
        inOrder.verify(console).printLine("10/01/2012 || 1000   || 1000");

    }

}
