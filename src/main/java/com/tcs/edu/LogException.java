package com.tcs.edu;

/**
 * Created on 17.05.2022
 * Класс для исключений, наследует класс Exception
 *
 * @author Viktor Artashevich
 */
public class LogException extends RuntimeException {
    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }

    protected LogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
