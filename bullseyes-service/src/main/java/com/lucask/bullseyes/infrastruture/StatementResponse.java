package com.lucask.bullseyes.infrastruture;

import com.lucask.bullseyes.domain.statement.StatementStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementResponse {

    private String id;
    private String title;
    private StatementStatus status;
    private LocalDate date;
    private LocalDate paymentDate;
    private String category;
    private BigDecimal expected;
    private BigDecimal paid;
    private String observation;
}
