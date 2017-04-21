package com.bookStore.core.services.exceptions;

/**
 * Created by Andrada on 4/9/2017.
 */

public class BookExistsException extends RuntimeException {
    public BookExistsException() {
    }

    public BookExistsException(String message) {
        super(message);
    }

    public BookExistsException(Throwable cause) {
        super(cause);
    }
}
