package com.lucask.bullseyes.domain.statements;

import com.lucask.bullseyes.infrastruture.exceptions.UpdateException;

public class StatementDeleteException extends UpdateException {

	public StatementDeleteException() {
		super( String.format( "Não foi possivel remover o lançamento " ) );
	}
}
