package unitests;

import com.imen.bank.kata.Account;
import com.imen.bank.kata.StatementPrinter;
import com.imen.bank.kata.Transaction;
import com.imen.bank.kata.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Statement;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    @Mock
    TransactionRepository transactionRepository;
    private Account account;
    @Mock StatementPrinter statementPrinter;

    @Before
    public void initialise(){
        account = new Account(transactionRepository,statementPrinter);
    }

    @Test public void store_a_deposit_transaction(){

        account.deposit(100);
        verify(transactionRepository).addDeposit(100);

    }
    @Test public void store_a_withdrawal_transaction(){

        account.withdraw(100);
        verify(transactionRepository).addWithdrawal(100);

    }
    @Test public void print_a_statement(){
        List<Transaction> transactions = asList(new Transaction());
        given(transactionRepository.allTransactions()).willReturn(transactions);
        account.printStatement();
        verify(statementPrinter).print(transactions);

    }
}
