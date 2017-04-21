package com.bookStore.core.services.exceptions;

/**
 * Created by Andrada on 4/9/2017.
 */
public class BookDoesNotExist extends RuntimeException {
    public BookDoesNotExist() {
    }

    public BookDoesNotExist(String message) {
        super(message);
    }

    public BookDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDoesNotExist(Throwable cause) {
        super(cause);
    }
}
