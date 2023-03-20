package com.leaning.userApp.exception;

/**
 * @author rajatha.kunj
 */
public class DaoException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new dao exception.
     *
     * @param message   the message
     * @param exception the exception
     */
    public DaoException(String message, Exception exception) {
        super(message, exception);
    }

    /**
     * Instantiates a new dao exception.
     *
     * @param exception the exception
     */
    public DaoException(Exception exception) {
        super(exception.getMessage(), exception);
    }

    /**
     * Instantiates a new dao exception.
     *
     * @param message the message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new dao exception.
     */
    public DaoException() {
    }
}