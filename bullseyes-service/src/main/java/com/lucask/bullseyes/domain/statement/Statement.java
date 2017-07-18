package com.lucask.bullseyes.domain.statement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statements")
public class Statement {

    @Id
    private String id;
    @NotEmpty
    private String title;
    private StatementStatus status;
    private LocalDate date;
    private LocalDate paymentDate;
    private String category;
    private BigDecimal expected;
    private BigDecimal paid;
    private String observation;

    public void open() {
        this.status = StatementStatus.OPEN;
    }

    public void paid() {
        this.status = StatementStatus.PAID;
    }

    public void canceled() {
        this.status = StatementStatus.CANCELED;
    }

    public boolean isOpen() {
        return StatementStatus.OPEN.equals(this.status);
    }

    public boolean isCanceled() {
        return StatementStatus.CANCELED.equals(this.status);
    }

    public boolean isPaid() {
        return StatementStatus.PAID.equals(this.status);
    }
}
