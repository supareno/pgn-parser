package org.supareno.pgnparser.exception;

/**
 * Exception used when an exception occurs during parse operations.
 *
 * @author supareno
 * @since 3.0.0
 */
public class ParserException extends RuntimeException {

    public ParserException() {
    }

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
