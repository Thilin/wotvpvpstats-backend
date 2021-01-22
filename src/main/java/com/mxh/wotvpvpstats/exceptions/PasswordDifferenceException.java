package com.mxh.wotvpvpstats.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordDifferenceException extends RuntimeException {
    public PasswordDifferenceException(String s) {
        super(s);
    }
}
