package com.leaning.userApp.exception;


import org.springframework.http.HttpStatus;

import com.leaning.userApp.spec.ErrorBo;


/**
 * @author rajatha.kunj
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 8946471906068816568L;
    protected ErrorBo error;

    protected HttpStatus status;

    public ServiceException(ErrorBo error, Exception exception, HttpStatus status) {
        super(exception);
        this.error = error;
        this.status = status;
    }


    public ServiceException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }


    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }


    public ServiceException(Exception exception) {
        super(exception.getMessage(), exception);
    }

    public ServiceException() {
    }

    public ServiceException(int value, String message) {
    }
}
