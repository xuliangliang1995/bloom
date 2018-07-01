package com.bloom.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String username) {
		super("could not find user '" + username + "'.");
	}
}
