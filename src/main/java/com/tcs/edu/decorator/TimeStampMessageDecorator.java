package com.tcs.edu.decorator;

import java.time.Instant;

public class TimeStampMessageDecorator {
    public static String decorate(String message){
        message= Instant.now()+" " + message;
        return message;
    }
}
