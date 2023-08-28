package com.addressbook.excepition;

public class AddressBookException extends RuntimeException {

	private String message;

	public AddressBookException(String message) {
		super(message);
	}
	
}
