package io.github.fosstree.exceptions;

public class JsonDeserializationException extends Exception {

    public JsonDeserializationException() {
        super();
    }

    public JsonDeserializationException(String message) {
        super(message);
    }

    public JsonDeserializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonDeserializationException(Throwable cause) {
        super(cause);
    }

    protected JsonDeserializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
