package com.lucask.bullseyes.domain.statements;

import static com.lucask.bullseyes.domain.statements.StatementStatus.CANCELED;
import static com.lucask.bullseyes.domain.statements.StatementStatus.OPEN;
import static com.lucask.bullseyes.domain.statements.StatementStatus.PAID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lucask.bullseyes.infrastruture.MongoDocument;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statements")
public class Statement implements MongoDocument {

	@Id
	private String id;
	@NotEmpty
	private String title;
	@NotEmpty
	private StatementStatus status;
	@NotEmpty
	private LocalDate dueDate;
	private BigDecimal expected;
	private Payment payment;
	@NotEmpty
	private String category;
	private String observation;
	@CreatedDate
	private LocalTime createdDate;

	public void open() {
		this.status = OPEN;
	}

	public void paid() {
		this.status = PAID;
	}

	public void canceled() {
		this.status = CANCELED;
	}

	public boolean isOpen() {
		return OPEN.equals( this.status );
	}

	public boolean isCanceled() {
		return CANCELED.equals( this.status );
	}

	public boolean isPaid() {
		return PAID.equals( this.status );
	}

	@Override
	public boolean hasId() {
		return id != null && !id.trim().isEmpty();
	}
}
