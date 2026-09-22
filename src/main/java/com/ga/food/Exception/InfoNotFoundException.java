package com.ga.food.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InfoNotFoundException extends RuntimeException {
    public InfoNotFoundException(String msg){
        super(msg);
    }
}
