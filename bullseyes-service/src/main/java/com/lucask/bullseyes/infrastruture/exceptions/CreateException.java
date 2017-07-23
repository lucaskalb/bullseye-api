package com.lucask.bullseyes.infrastruture.exceptions;

public class CreateException extends BusinessException {

	public CreateException(final String message) {
		super( "e501", message );
	}
}
