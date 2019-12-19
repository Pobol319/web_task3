package com.epam.task.exception;

public class JsonReaderException extends Exception {
    public JsonReaderException() {
        super();
    }

    public JsonReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonReaderException(String message) {
        super(message);
    }

    public JsonReaderException(Throwable cause) {
        super(cause);
    }
}
