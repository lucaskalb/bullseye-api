package com.lucask.bullseyes.domain.statements;

import com.lucask.bullseyes.infrastruture.exceptions.UpdateException;

public class StatementUpdateException extends UpdateException {

	public StatementUpdateException(final String title) {
		super( String.format( "Não foi possivel atualizar '%s' ", title ) );
	}
}
