package com.leaning.userApp.exception;


import org.springframework.http.HttpStatus;

import com.leaning.userApp.spec.ErrorBo;


/**
 * @author rajatha.kunj
 */
public class DuplicateResourceFoundException extends  Exception{

    private static final long serialVersionUID = 5372876325002518021L;

    protected ErrorBo errorBo;

    protected HttpStatus status = HttpStatus.CONFLICT;

    /**
     * @param message
     * @param exception
     */
    public DuplicateResourceFoundException(String message, Exception exception) {
        super(message, exception);
    }

    /**
     * @param exception
     */
    public DuplicateResourceFoundException(Exception exception) {
        super(exception.getMessage(), exception);
    }

    /**
     * @param message
     */
    public DuplicateResourceFoundException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }

    public DuplicateResourceFoundException() {
    }

    public DuplicateResourceFoundException(ErrorBo errorBo, Throwable exception) {
        this(errorBo, exception, HttpStatus.CONFLICT);
    }

    public DuplicateResourceFoundException(ErrorBo errorBo, Throwable exception, HttpStatus status) {
        super(exception);
        this.errorBo = errorBo;
        this.status = status;
    }

    public DuplicateResourceFoundException(ErrorBo errorBo) {
        this.errorBo = errorBo;
        this.status = HttpStatus.CONFLICT;
    }

    public DuplicateResourceFoundException(ErrorBo errorBo, HttpStatus conflict) {
        this.errorBo = errorBo;
        this.status = HttpStatus.CONFLICT;
    }

    public ErrorBo getErrorBo() {
        return errorBo;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
