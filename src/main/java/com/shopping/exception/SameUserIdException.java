package com.shopping.exception;

public class SameUserIdException extends RuntimeException{

    public SameUserIdException() {
        super();
    }

    public SameUserIdException(String message) {
        super(message);
    }

    public SameUserIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameUserIdException(Throwable cause) {
        super(cause);
    }

}
