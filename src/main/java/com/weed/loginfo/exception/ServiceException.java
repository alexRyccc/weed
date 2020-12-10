package com.weed.loginfo.exception;

/**
 * @author: godslhand
 * @date: 2019/1/15
 * @description:
 */
public class ServiceException extends RuntimeException {

    private String errorCode;
    public static final String DEFAULT_ERROR_CODE = "BIZ_ERROR";

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, String message, Throwable cause) {
        super( message, cause);
        this.errorCode = errorCode;
    }
}
