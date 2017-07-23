package com.lucask.bullseyes.infrastruture.resources.statements;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.lucask.bullseyes.domain.statements.Statement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementCreateData {

	private String title;
	private LocalDate dueDate;
	private BigDecimal expected;
	private String category;
	private String observation;

	public Statement toEntity() {
		return Statement.builder()
				.title( this.title )
				.dueDate( this.dueDate )
				.expected( this.expected )
				.category( this.category )
				.observation( this.observation )
				.build();
	}
}
