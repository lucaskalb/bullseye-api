package com.lucask.bullseyes.infrastruture.exceptions;

public class DeleteException extends BusinessException {

	public DeleteException(final String message) {
		super( "e503", message );
	}
}
