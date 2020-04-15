package com.todo.api.utils.exception;

public class CEmailSigninFailedException extends RuntimeException {

    public CEmailSigninFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CEmailSigninFailedException(String message) {
        super(message);
    }

    public CEmailSigninFailedException() {
        super();
    }

}
