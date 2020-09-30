package com.mobile.mobilehardware.exceptions;

/**
 * @author guxiaonian
 */
public class MobException extends Exception {

    public MobException(String error) {
        super(error);
    }

    public MobException(Throwable throwable) {
        super(throwable);
    }

}
