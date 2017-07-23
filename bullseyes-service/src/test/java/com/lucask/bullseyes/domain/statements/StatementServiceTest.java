package com.lucask.bullseyes.domain.statements;

import static com.lucask.bullseyes.domain.statements.StatementHelper.getHouseRent;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementServiceTest {

	@Mock
	StatementRepository repository;

	@InjectMocks
	StatementServiceImpl service;

	@Test
	public void should_create_statement_with_successfully() {
		final Statement houseRent = getHouseRent();

		when( repository.save( Mockito.any( Statement.class ) ) ).thenReturn( houseRent );

		final Statement statement = service.create( houseRent );

		assertThat( statement.getTitle(), equalTo( houseRent.getTitle() ) );
		assertThat( statement.getStatus(), equalTo( StatementStatus.OPEN ) );
		assertThat( statement.getCategory(), equalTo( houseRent.getCategory() ) );
		assertThat( statement.getDueDate(), equalTo( houseRent.getDueDate() ) );
		assertThat( statement.getExpected(), equalTo( houseRent.getExpected() ) );
		assertThat( statement.getObservation(), equalTo( houseRent.getObservation() ) );
	}

	@Test
	public void should_throw_error_on_create_statement_with_status_paid() {
		final Statement houseRent = getHouseRent();
		houseRent.paid();
		service.create( houseRent );
	}

	@Test
	public void should_update_statement_with_successfully() {
		final Statement houseRent = getHouseRent();

		when( repository.save( Mockito.any( Statement.class ) ) ).thenReturn( houseRent );

		final Statement statement = service.update( houseRent );

		assertThat( statement.getTitle(), equalTo( houseRent.getTitle() ) );
		assertThat( statement.getStatus(), equalTo( houseRent.getStatus() ) );
		assertThat( statement.getCategory(), equalTo( houseRent.getCategory() ) );
		assertThat( statement.getDueDate(), equalTo( houseRent.getDueDate() ) );
		assertThat( statement.getExpected(), equalTo( houseRent.getExpected() ) );
		assertThat( statement.getObservation(), equalTo( houseRent.getObservation() ) );
	}

	@Test
	public void should_delete_statement_with_successfully_when_canceled() {
		final Statement houseRent = getHouseRent();
		houseRent.canceled();

		when( repository.findOne( anyString() ) ).thenReturn( houseRent );

		service.delete( "12312-123-1-1-212-3" );
		verify( repository ).delete( Mockito.any( Statement.class ) );
	}

	@Test(expected = StatementDeleteException.class)
	public void should_throw_error_when_delete_statement_paid() {
		final Statement houseRent = getHouseRent();
		houseRent.paid();

		when( repository.findOne( anyString() ) ).thenReturn( houseRent );

		service.delete( "12312-123-1-1-212-3" );
	}

	@Test(expected = StatementDeleteException.class)
	public void should_throw_error_when_delete_statement_open() {
		final Statement houseRent = getHouseRent();
		houseRent.open();

		when( repository.findOne( anyString() ) ).thenReturn( houseRent );

		service.delete( "12312-123-1-1-212-3" );
	}

	@Test
	public void should_found_statement_by_id() {
		final Statement houseRent = getHouseRent();

		when( repository.findOne( anyString() ) ).thenReturn( houseRent );

		final Statement statement = service.find( "12312-123-1-1-212-3" );

		assertThat( statement, notNullValue() );
		assertThat( statement.getTitle(), equalTo( houseRent.getTitle() ) );
	}

	@Test
	public void should_not_found_statement_when_id_non_exists() {
		final Statement houseRent = getHouseRent();

		when( repository.findOne( anyString() ) ).thenReturn( null );

		assertThat( service.find( "12312-123-1-1-212-3" ), nullValue() );
	}

}
