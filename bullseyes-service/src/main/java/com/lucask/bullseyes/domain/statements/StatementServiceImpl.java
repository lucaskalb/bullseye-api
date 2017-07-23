package com.lucask.bullseyes.domain.statements;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StatementServiceImpl implements StatementService {

	@Autowired
	private StatementRepository repository;

	@Override
	public Statement create(final Statement statement) {
		if (statement.hasId() || !statement.isOpen())
			throw new StatementCreateException( statement.getTitle() );

		return repository.save( statement );
	}

	@Override
	public Statement update(final Statement statement) {
		if (!statement.hasId())
			throw new StatementUpdateException( statement.getTitle() );

		if (statement.isPaid())
			throw new StatementUpdateToPaidException();

		return repository.save( statement );
	}

	@Override
	public void delete(final String id) {
		final Statement statement = repository.findOne( id );

		if (!statement.isCanceled())
			throw new StatementDeleteException();

		repository.delete( statement );
	}

	@Override
	public Statement find(final String id) {
		return repository.findOne( id );
	}

}
