package com.lucask.bullseyes.domain.statement;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class StatementHelper {

    public static Statement getHouseRent() {
        return Statement.builder()
                .title("House Rent")
                .status(StatementStatus.PAID)
                .category("home")
                .date(LocalDate.now().minusDays(1))
                .paymentDate(LocalDate.now())
                .expected(BigDecimal.valueOf(1000L))
                .paid(BigDecimal.valueOf(1100L))
                .observation("Rent of house was paid late, there was a fine charge.")
                .build();
    }
}
