package com.mxh.wotvpvpstats.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoConfrontationException extends RuntimeException {
    public NoConfrontationException(String no_confrontation_registered) {
    }
}
