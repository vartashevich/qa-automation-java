package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Created on 24.03.2022
 *
 * @author Viktor Artashevich
 */

public class TimeStampMessageDecorator {
    /**
     * @param message сообщение, передаваемое для декорирования в виде добавления даты
     * @return Возвращаем переменную decoratedMessage - результат декорирования сообщения message
     */
    public static String decorate(String message) {

        String decoratedMessage = Instant.now().toString() + " " + message;
        return decoratedMessage;
    }
}
