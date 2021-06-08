package ru.otus.ageev.exeptions;

/**
 * An exception that provides information on an application error.
 *
 * @author Anatoliy Ageev
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 8288779062647218916L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

}

