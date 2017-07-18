package com.lucask.bullseyes.domain.statement;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.lucask.bullseyes.domain.statement.StatementHelper.getHouseRent;

@RunWith(MockitoJUnitRunner.class)
public class StatementServiceTest {

    @Mock
    StatementRepository repository;

    @InjectMocks
    StatementServiceImpl service;

    @Test
    public void should_create_statement_with_successfully() {
        Statement houseRent = getHouseRent();

        Mockito.when(repository.save(Mockito.any(Statement.class))).thenReturn(houseRent);

        Statement statement = service.create(houseRent);

        Assert.assertThat(statement.getTitle(), Matchers.equalTo(houseRent.getTitle()));
        Assert.assertThat(statement.getStatus(), Matchers.equalTo(houseRent.getStatus()));
        Assert.assertThat(statement.getCategory(), Matchers.equalTo(houseRent.getCategory()));
        Assert.assertThat(statement.getDate(), Matchers.equalTo(houseRent.getDate()));
        Assert.assertThat(statement.getPaymentDate(), Matchers.equalTo(houseRent.getPaymentDate()));
        Assert.assertThat(statement.getExpected(), Matchers.equalTo(houseRent.getExpected()));
        Assert.assertThat(statement.getPaid(), Matchers.equalTo(houseRent.getPaid()));
        Assert.assertThat(statement.getObservation(), Matchers.equalTo(houseRent.getObservation()));
    }

}
