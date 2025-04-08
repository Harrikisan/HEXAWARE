package com.hexaware.electronicgadgets.exceptions;

public class DatabaseOperationException extends Exception{
	public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
