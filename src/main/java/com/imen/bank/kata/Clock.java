package com.imen.bank.kata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {
    private static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String todayAsString() {
        LocalDate today = LocalDate.now();
        return today.format(DD_MM_YYYY);
    }

    protected LocalDate today() {
        return LocalDate.now();
    }
}
