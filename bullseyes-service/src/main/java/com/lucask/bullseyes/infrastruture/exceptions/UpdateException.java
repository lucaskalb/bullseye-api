package com.lucask.bullseyes.infrastruture.exceptions;

public class UpdateException extends BusinessException {

	public UpdateException(final String message) {
		super( "e502", message );
	}
}
