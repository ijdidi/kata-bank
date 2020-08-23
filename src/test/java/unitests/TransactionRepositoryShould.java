package unitests;

import com.imen.bank.kata.Clock;
import com.imen.bank.kata.Transaction;
import com.imen.bank.kata.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    private static final String TODAY = "23/08/2020";
    private TransactionRepository transactionRepository;
    @Mock
    Clock clock;

    @Before
    public void initialise() {
        transactionRepository = new TransactionRepository(clock);
        given(clock.todayAsString()).willReturn(TODAY);
    }

    @Test
    public void create_and_store_a_deposit_transaction() {
        transactionRepository.addDeposit(100);
        List<Transaction> transactions = transactionRepository.allTransactions();
        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction(TODAY, 100)));
    }

    private Transaction transaction(String date, int amount) {
        return new Transaction(date, amount);
    }

    @Test
    public void create_and_store_a_withdrawal_transaction() {

        transactionRepository.addWithdrawal(100);
        List<Transaction> transactions = transactionRepository.allTransactions();
        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction(TODAY, -100)));
    }
}
