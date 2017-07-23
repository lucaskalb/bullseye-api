package com.lucask.bullseyes.domain.statements;

import static com.lucask.bullseyes.domain.statements.StatementHelper.getEnergyCompany;
import static com.lucask.bullseyes.domain.statements.StatementHelper.getHouseRent;
import static com.lucask.bullseyes.infrastruture.resources.statements.StatementResource.DEFAULT_PATH;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucask.bullseyes.BullseyesApplicationTests;

public class StatementIntegrationTest extends BullseyesApplicationTests {

	@Autowired
	private StatementServiceImpl service;

	@Autowired
	StatementRepository repository;

	@Test
	public void should_create_statement() throws Exception {
		perform( post( DEFAULT_PATH, getHouseRent() ) )
				.andExpect( jsonPath( "$.id", notNullValue() ) );
	}

	@Test
	public void should_update_statement() throws Exception {
		final Statement saved = service.create( getEnergyCompany() );

		final String titleChanged = "Home Energy";
		saved.setTitle( titleChanged );

		perform( put( DEFAULT_PATH, titleChanged ) )
				.andExpect( jsonPath( "$.id", equalTo( saved.getId() ) ) )
				.andExpect( jsonPath( "$.title", equalTo( saved.getTitle() ) ) );
	}

}
