package com.lucask.bullseyes.infrastruture;

import com.lucask.bullseyes.domain.statement.Statement;
import org.springframework.stereotype.Component;

@Component
public class StatementResponseMapper implements Mapper<Statement, StatementResponse> {

    @Override
    public StatementResponse map(Statement source) {
        return StatementResponse.builder()
                .id(source.getId())
                .title(source.getTitle())
                .status(source.getStatus())
                .category(source.getCategory())
                .date(source.getDate())
                .expected(source.getExpected())
                .observation(source.getObservation())
                .paid(source.getPaid())
                .paymentDate(source.getPaymentDate())
                .build();
    }
}
