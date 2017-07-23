package com.lucask.bullseyes.infrastruture.resources.statements;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.lucask.bullseyes.domain.statements.Statement;
import com.lucask.bullseyes.domain.statements.StatementStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementResponse {

	private String id;
	private String title;
	private StatementStatus status;
	private LocalDate date;
	private String category;
	private BigDecimal expected;
	private String observation;

	public static StatementResponse process(final Statement statement) {
		return StatementResponse.builder()
				.id( statement.getId() )
				.title( statement.getTitle() )
				.status( statement.getStatus() )
				.category( statement.getCategory() )
				.date( statement.getDueDate() )
				.expected( statement.getExpected() )
				.observation( statement.getObservation() )
				.build();
	}
}
