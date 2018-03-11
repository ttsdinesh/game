package com.rpg.exception;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 10-Mar-2018
 */
public class RpgException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RpgException(Exception rtEx) {
		super(rtEx);
	}

	public RpgException(String message) {
		super(message);
	}
}
