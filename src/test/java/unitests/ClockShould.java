package unitests;

import com.imen.bank.kata.Clock;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ClockShould {
    @Test
    public void returns_todays_date() {
        Clock clock = new TestableClock();
        String date = clock.todayAsString();
        assertThat(date, is("23/08/2020"));

    }

    private class TestableClock extends Clock {
        @Override
        protected LocalDate today() {
            return super.today();
        }
    }
}
