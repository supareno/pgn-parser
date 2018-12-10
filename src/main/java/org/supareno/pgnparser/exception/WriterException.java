package org.supareno.pgnparser.exception;

/**
 * Exception used when an exception occurs during write operations.
 *
 * @author supareno
 * @since 3.0.0
 */
public class WriterException extends RuntimeException {

    public WriterException() {
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
