package com.lucask.bullseyes.domain.statements;

import com.lucask.bullseyes.infrastruture.exceptions.CreateException;

public class StatementCreateException extends CreateException {

	public StatementCreateException(final String title) {
		super( String.format( "Não foi possivel salvar o lançamento '%s'", title ) );
	}
}
