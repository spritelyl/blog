package com.jxust.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sxtstart
 * @create 2020-02-09 16:01
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFindException extends RuntimeException{

    public NotFindException() {
    }

    public NotFindException(String message) {
        super(message);
    }

    public NotFindException(String message, Throwable cause) {
        super(message, cause);
    }

}
