package se.lexicon.exception;

public class AirlineException extends Exception {
	public AirlineException() { super(); }
	public AirlineException(String message) { super(message); }
	public AirlineException(String message, Throwable cause) { super(message, cause); }
}
