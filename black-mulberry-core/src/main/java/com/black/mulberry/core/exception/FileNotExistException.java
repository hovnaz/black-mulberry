package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the requested file does not exist.
 */
public class FileNotExistException extends RuntimeException {

    /**
     * Constructs a new FileNotExistException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public FileNotExistException(String message) {
        super(message);
    }
}
