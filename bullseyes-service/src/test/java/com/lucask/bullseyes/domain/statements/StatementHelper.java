package com.lucask.bullseyes.domain.statements;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatementHelper {

	public static Statement getHouseRent() {
		return Statement.builder()
				.title( "House Rent" )
				.status( StatementStatus.OPEN )
				.category( "home" )
				.dueDate( LocalDate.now().minusDays( 1 ) )
				.payment( Payment.builder()
						.date( LocalDate.now() )
						.value( BigDecimal.valueOf( 1100L ) )
						.build() )
				.expected( BigDecimal.valueOf( 1000L ) )
				.observation( "Rent of house was paid late, there was a fine charge." )
				.build();
	}

	public static Statement getEnergyCompany() {
		return Statement.builder()
				.title( "Energy" )
				.status( StatementStatus.OPEN )
				.category( "home" )
				.dueDate( LocalDate.now().minusDays( 2 ) )
				.expected( BigDecimal.valueOf( 140L ) )
				.observation( "This is the home energy consumption" )
				.build();

	}
}
