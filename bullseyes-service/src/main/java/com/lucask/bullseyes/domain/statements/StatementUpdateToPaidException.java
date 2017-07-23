package com.lucask.bullseyes.domain.statements;

import com.lucask.bullseyes.infrastruture.exceptions.UpdateException;

public class StatementUpdateToPaidException extends UpdateException {

	public StatementUpdateToPaidException() {
		super( "Não é possivel atualizar o lançamento para pago" );
	}
}
