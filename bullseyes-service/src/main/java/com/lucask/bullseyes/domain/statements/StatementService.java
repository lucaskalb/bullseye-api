package com.lucask.bullseyes.domain.statements;

public interface StatementService {

	public Statement create(Statement statement);

	public Statement update(Statement statement);

	void delete(String id);

	Statement find(String id);

}
